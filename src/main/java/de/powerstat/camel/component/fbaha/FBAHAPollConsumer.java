/*
 * Copyright (C) 2022-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.camel.component.fbaha;


import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.support.ScheduledPollConsumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.javatuples.Pair;
import org.javatuples.Quintet;
import org.xml.sax.SAXException;

import de.powerstat.fb.mini.AHASessionMini;
import de.powerstat.fb.mini.AIN;
import de.powerstat.fb.mini.ApplyMask;
import de.powerstat.fb.mini.Energy;
import de.powerstat.fb.mini.Functions;
import de.powerstat.fb.mini.Hs;
import de.powerstat.fb.mini.Metadata;
import de.powerstat.fb.mini.Power;
import de.powerstat.fb.mini.SubscriptionState;
import de.powerstat.fb.mini.TemperatureCelsius;
import de.powerstat.fb.mini.TemperatureKelvin;
import de.powerstat.fb.mini.Template;
import de.powerstat.fb.mini.Trigger;
import de.powerstat.fb.mini.UnixTimestamp;
import de.powerstat.fb.mini.Voltage;
import de.powerstat.validation.values.Percent;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


/**
 * FB AHA poll consumer.
 *
 * TODO exchange output as nativ, alwaysXML, json
 * TODO header
 * TODO body
 */
public class FBAHAPollConsumer extends ScheduledPollConsumer
 {
  /**
   * AHA session.
   */
  private AHASessionMini api;

  /**
   * FB H configuration.
   */
  private final FBAHAConfiguration conf;

  /**
   * Camel context.
   */
  private final CamelContext context;

  /**
   * Endpoint.
   */
  private final FBAHAEndpoint endpoint;

  /**
   * Result cache with switchcmd and ain as key and latest result as value.
   */
  private final Map<String, String> resultCache = new ConcurrentHashMap<>();


  /**
   * Constructor.
   *
   * @param defaultEndpoint Default endpoint
   * @param processor Processor
   */
  @SuppressFBWarnings({"EI_EXPOSE_REP2"})
  public FBAHAPollConsumer(final DefaultEndpoint defaultEndpoint, final Processor processor)
   {
    super(defaultEndpoint, processor);
    context = defaultEndpoint.getCamelContext();
    endpoint = ((FBAHAEndpoint)defaultEndpoint);
    conf = endpoint.getConfiguration();
   }


  /**
   * Switchcmd with ain result cache handler.
   *
   * @param switchcmdAin Switchcmd with ain if one
   * @param result Result from actual call.
   * @return Result or null if conf.onlyOnChange is true, otherwise always result
   */
  private @Nullable String cacheHandler(final String switchcmdAin, final @Nullable String result)
   {
    if (conf.isOnlyOnChange() && (result != null))
     {
      final String lastResult = resultCache.get(switchcmdAin);
      if ((lastResult != null) && lastResult.equals(result))
       {
        return null;
       }
      resultCache.put(switchcmdAin, result);
     }
    return result;
   }


  /**
   * Execute switch command.
   *
   * @param switchcmd Switch command
   * @return Result of switch command or null
   * @throws TransformerFactoryConfigurationError Transformer factory configuration error
   */
  @SuppressFBWarnings({"CC_CYCLOMATIC_COMPLEXITY", "FII_USE_METHOD_REFERENCE"})
  private @Nullable String executeSwitchCmd(final String switchcmd) throws TransformerFactoryConfigurationError
   {
    var result = ""; //$NON-NLS-1$
    try
     {
      switch (switchcmd)
       {
        case "getswitchlist": //$NON-NLS-1$
          final List<AIN> switches = api.getSwitchList();
          result = switches.stream().map(AIN::stringValue).collect(Collectors.joining(", ")); //$NON-NLS-1$
          break;
        case "getswitchstate": //$NON-NLS-1$
          result = String.valueOf(api.getSwitchState(AIN.of(conf.getAin())));
          break;
        case "getswitchpresent": //$NON-NLS-1$
          result = String.valueOf(api.isSwitchPresent(AIN.of(conf.getAin())));
          break;
        case "getswitchpower": //$NON-NLS-1$
          result = String.valueOf(api.getSwitchPower(AIN.of(conf.getAin())).getPowerWatt());
          break;
        case "getswitchenergy": //$NON-NLS-1$
          result = String.valueOf(api.getSwitchEnergy(AIN.of(conf.getAin())).longValue());
          break;
        case "getswitchname": //$NON-NLS-1$
          result = api.getSwitchName(AIN.of(conf.getAin()));
          break;
        case "getdevicelistinfos": //$NON-NLS-1$
          result = api.getDeviceListInfos().toString(); // TODO
          break;
        case "gettemperature": //$NON-NLS-1$
          result = String.valueOf(api.getTemperature(AIN.of(conf.getAin())).getTemperatureCelsius());
          break;
        case "gethkrtsoll": //$NON-NLS-1$
          result = String.valueOf(api.getHkrtSoll(AIN.of(conf.getAin())).getTemperatureCelsius());
          break;
        case "gethkrkomfort": //$NON-NLS-1$
          result = String.valueOf(api.getHkrKomfort(AIN.of(conf.getAin())).getTemperatureCelsius());
          break;
        case "gethkrabsenk": //$NON-NLS-1$
          result = String.valueOf(api.getHkrAbsenk(AIN.of(conf.getAin())).getTemperatureCelsius());
          break;
        case "getbasicdevicestats": //$NON-NLS-1$
         {
          final StringBuilder buffer = new StringBuilder();
          final Quintet<SortedMap<UnixTimestamp, TemperatureCelsius>, SortedMap<UnixTimestamp, Percent>, SortedMap<UnixTimestamp, Voltage>, SortedMap<UnixTimestamp, Power>, SortedMap<UnixTimestamp, Energy>> devStats = api.getBasicDeviceStats(AIN.of(conf.getAin()));
          final SortedMap<UnixTimestamp, TemperatureCelsius> tempCel = devStats.getValue0();
          if (!tempCel.isEmpty())
           {
            buffer.append("temperature");
            for (final Map.Entry<UnixTimestamp, TemperatureCelsius> entry : tempCel.entrySet())
             {
              buffer.append(", ").append(entry.getKey().longValue()).append(':').append(entry.getValue().getTemperatureCelsius());
             }
            buffer.append("\n");
           }
          final SortedMap<UnixTimestamp, Percent> humidity = devStats.getValue1();
          if (!humidity.isEmpty())
           {
            buffer.append("humidity");
            for (final Map.Entry<UnixTimestamp, Percent> entry : humidity.entrySet())
             {
              buffer.append(", ").append(entry.getKey().longValue()).append(':').append(entry.getValue().intValue());
             }
            buffer.append("\n");
           }
          final SortedMap<UnixTimestamp, Voltage> voltage = devStats.getValue2();
          if (!voltage.isEmpty())
           {
            buffer.append("voltage");
            for (final Map.Entry<UnixTimestamp, Voltage> entry : voltage.entrySet())
             {
              buffer.append(", ").append(entry.getKey().longValue()).append(':').append(entry.getValue().longValue());
             }
            buffer.append("\n");
           }
          final SortedMap<UnixTimestamp, Power> power = devStats.getValue3();
          if (!power.isEmpty())
           {
            buffer.append("power");
            for (final Map.Entry<UnixTimestamp, Power> entry : power.entrySet())
             {
              buffer.append(", ").append(entry.getKey().longValue()).append(':').append(entry.getValue().longValue());
             }
            buffer.append("\n");
           }
          final SortedMap<UnixTimestamp, Energy> energy = devStats.getValue4();
          if (!energy.isEmpty())
           {
            buffer.append("energy");
            for (final Map.Entry<UnixTimestamp, Energy> entry : energy.entrySet())
             {
              buffer.append(", ").append(entry.getKey().longValue()).append(':').append(entry.getValue().longValue());
             }
            buffer.append("\n");
           }
          result = buffer.toString();
          break;
         }
        case "gettemplatelistinfos": //$NON-NLS-1$
         {
          final StringBuilder buffer = new StringBuilder();
          final List<Template> templInfos = api.getTemplateListInfos();
          for (final Template templ : templInfos)
           {
            final AIN ain = templ.getIdentifier();
            final long id = templ.getId();
            final boolean autocreate = templ.getAutocreate();
            final String name = templ.getName();
            final Metadata metadata = templ.getMetadata();
            buffer.append(ain).append(", ").append(id).append(", ").append(autocreate).append(", ").append(name).append(", ").append(metadata.stringValue());
            final EnumSet<Functions> functions = templ.getFunctionbitmask();
            buffer.append(", ").append(functions.toString()); // TODO
            final EnumSet<ApplyMask> applymask = templ.getApplymask();
            buffer.append(", ").append(applymask.toString()); // TODO
            // TODO
            final List<AIN> devices = templ.getDevices();
            final List<AIN> subtemplates = templ.getSubtemplates();
            final List<AIN> triggers = templ.getTriggers();
           }
          result = buffer.toString();
          break;
         }
        case "getcolordefaults": //$NON-NLS-1$
         {
          final StringBuilder buffer = new StringBuilder();
          final Pair<List<Hs>, List<TemperatureKelvin>> colorDefaults = api.getColorDefaults();
          final List<Hs> hss = colorDefaults.getValue0();
          final List<TemperatureKelvin> kelvins = colorDefaults.getValue1();
          if (!hss.isEmpty())
           {
            for (final Hs hs : hss)
             {
              buffer.append("hs, ").append(hs.stringValue()).append("\n");
             }
           }
          if (!kelvins.isEmpty())
           {
            buffer.append("temperaturedefaults");
            for (final TemperatureKelvin kelvin : kelvins)
             {
              buffer.append(", ").append(kelvin.intValue());
             }
            buffer.append("\n");
           }
          result = buffer.toString();
          break;
         }
        case "getsubscriptionstate": //$NON-NLS-1$
          final SubscriptionState subStat = api.getSubscriptionState();
          result = subStat.subscriptionCodeValue() + ", " + subStat.ainValue();
          break;
        case "gettriggerlistinfos": //$NON-NLS-1$
          final List<Trigger> triggers = api.getTriggerListInfos();
          final StringBuilder buffer = new StringBuilder();
          for (final Trigger trigger : triggers)
           {
            buffer.append(trigger.ainValue()).append(", ").append(trigger.isActive()).append(", ").append(trigger.stringValue()).append("\n");
           }
          result = buffer.toString();
          break;
        case "getdeviceinfos": //$NON-NLS-1$
          result = api.getDeviceInfos(AIN.of(conf.getAin())).toString(); // TODO XML?
          break;
        default:
          result = null;
       }
     }
    catch (final IOException | SAXException | TransformerFactoryConfigurationError e)
     {
      result = null;
     }
    return cacheHandler(switchcmd + (conf.getAin() == null ? "" : conf.getAin()), result); //$NON-NLS-1$
   }


  /**
   * Handle switch cmd.
   *
   * @return Exchange with result or null.
   * @throws TransformerFactoryConfigurationError Transformer factory configuration error
   * @throws IllegalArgumentException Illegal or missing argument
   */
  private @Nullable Exchange handleSwitchCmd() throws TransformerFactoryConfigurationError
   {
    final String switchcmd = conf.getSwitchcmd();
    if (!conf.parameterCheck())
     {
      throw new IllegalArgumentException("Missing arguments for switchcmd: " + switchcmd); //$NON-NLS-1$
     }
    final String result = executeSwitchCmd(switchcmd);
    if (result == null)
     {
      return null;
     }
    final Exchange exchange = new DefaultExchange(context);
    exchange.getMessage().setBody(result);
    return exchange;
   }


  /**
   * Poll.
   *
   * @return Number of messages polled, will be 0 if no message was polled at all.
   * @throws Exception Can be thrown if an exception occurred during polling
   */
  @Override
  protected int poll() throws Exception
   {
    api = endpoint.getApiProxy();
    final var exchange = handleSwitchCmd();
    if (exchange == null)
     {
      // processEmptyMessage();
      return 0;
     }
    getProcessor().process(exchange);
    return 1;
   }

 }
