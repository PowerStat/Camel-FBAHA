/*
 * Copyright (C) 2022-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.camel.component.fbaha;


import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.SortedMap;
import java.util.stream.Collectors;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.support.DefaultProducer;
import org.javatuples.Pair;
import org.javatuples.Quintet;
import org.xml.sax.SAXException;

import de.powerstat.fb.mini.AHASessionMini;
import de.powerstat.fb.mini.AHASessionMini.HandleBlind;
import de.powerstat.fb.mini.AIN;
import de.powerstat.fb.mini.DurationMS100;
import de.powerstat.fb.mini.EndTimestamp;
import de.powerstat.fb.mini.Energy;
import de.powerstat.fb.mini.Hs;
import de.powerstat.fb.mini.Hue;
import de.powerstat.fb.mini.Level;
import de.powerstat.fb.mini.Metadata;
import de.powerstat.fb.mini.Power;
import de.powerstat.fb.mini.Saturation;
import de.powerstat.fb.mini.ScenarioType;
import de.powerstat.fb.mini.SubscriptionState;
import de.powerstat.fb.mini.TemperatureCelsius;
import de.powerstat.fb.mini.TemperatureKelvin;
import de.powerstat.fb.mini.Template;
import de.powerstat.fb.mini.Trigger;
import de.powerstat.fb.mini.UnixTimestamp;
import de.powerstat.fb.mini.Voltage;
import de.powerstat.validation.values.Percent;
import de.powerstat.validation.values.Seconds;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


/**
 * FBMini producer.
 *
 * TODO support headers
 * TODO support body
 * TODO exchange input: nativ, xml, json
 * TODO exchange output: nativ, alwaysXML, json
 */
public class FBAHAProducer extends DefaultProducer
 {
  /**
   * FBMini endpoint.
   */
  private final FBAHAEndpoint endpoint;


  /**
   * Constructor.
   *
   * @param endpoint Endpoint
   */
  @SuppressFBWarnings({"EI_EXPOSE_REP2"})
  public FBAHAProducer(final FBAHAEndpoint endpoint)
   {
    super(endpoint);
    this.endpoint = endpoint;
   }


  /**
   * Execute switch command.
   *
   * @param conf FBAHAConfiguration
   * @param api AHASessionMini
   * @param switchcmd Switch command
   * @return Switch command result
   * @throws IOException IO exception
   * @throws TransformerFactoryConfigurationError Transformer factory configuration error
   * @throws TransformerConfigurationException Transformer configuration exception
   * @throws TransformerException Transformer exception
   * @throws SAXException SAX exception
   */
  @SuppressFBWarnings({"CC_CYCLOMATIC_COMPLEXITY", "FII_USE_METHOD_REFERENCE"})
  private static String executeSwitchCmd(final FBAHAConfiguration conf, final AHASessionMini api, final String switchcmd) throws IOException, TransformerFactoryConfigurationError, TransformerException, SAXException
   {
    var result = "";
    switch (switchcmd)
     {
      case "setswitchon": //$NON-NLS-1$
        result = String.valueOf(api.setSwitchOn(AIN.of(conf.getAin())));
        break;
      case "setswitchoff": //$NON-NLS-1$
        result = String.valueOf(api.setSwitchOff(AIN.of(conf.getAin())));
        break;
      case "setswitchtoggle": //$NON-NLS-1$
        result = String.valueOf(api.setSwitchToggle(AIN.of(conf.getAin())));
        break;
      case "sethkrtsoll": //$NON-NLS-1$
        api.setHkrtSoll(AIN.of(conf.getAin()), TemperatureCelsius.of(conf.getTemperature()));
        break;
      case "applytemplate": //$NON-NLS-1$
        api.applyTemplate(AIN.of(conf.getAin()));
        break;
      case "setsimpleonoff": //$NON-NLS-1$
        api.setSimpleOnOff(AIN.of(conf.getAin()), AHASessionMini.HandleOnOff.values()[conf.getOnoff()]);
        break;
      case "setlevel": //$NON-NLS-1$
        api.setLevel(AIN.of(conf.getAin()), Level.of(conf.getLevel()));
        break;
      case "setlevelpercentage": //$NON-NLS-1$
        api.setLevelPercentage(AIN.of(conf.getAin()), Percent.of(conf.getLevel()));
        break;
      case "setcolor": //$NON-NLS-1$
        api.setColor(AIN.of(conf.getAin()), Hue.of(conf.getHue()), Saturation.of(conf.getSaturation()), DurationMS100.of(conf.getDuration()));
        break;
      case "setcolortemperature": //$NON-NLS-1$
        api.setColorTemperature(AIN.of(conf.getAin()), TemperatureKelvin.of((int)conf.getTemperature()), DurationMS100.of(conf.getDuration()));
        break;
      case "sethkrboost": //$NON-NLS-1$
        result = String.valueOf(api.setHkrBoost(AIN.of(conf.getAin()), EndTimestamp.of(Seconds.of(conf.getEndtimestamp()))));
        break;
      case "sethkrwindowopen": //$NON-NLS-1$
        result = String.valueOf(api.setHkrWindowOpen(AIN.of(conf.getAin()), EndTimestamp.of(Seconds.of(conf.getEndtimestamp()))));
        break;
      case "setblind": //$NON-NLS-1$
        api.setBlind(AIN.of(conf.getAin()), HandleBlind.valueOf(conf.getTarget().toUpperCase(Locale.getDefault())));
        break;
      case "setname": //$NON-NLS-1$
        api.setName(AIN.of(conf.getAin()), conf.getName());
        break;
      case "startulesubscription": //$NON-NLS-1$
        api.startUleSubscription();
        break;
      case "setunmappedcolor": //$NON-NLS-1$
        api.setUnmappedColor(AIN.of(conf.getAin()), Hue.of(conf.getHue()), Saturation.of(conf.getSaturation()), DurationMS100.of(conf.getDuration()));
        break;
      case "setmetadata": //$NON-NLS-1$
        api.setMetaData(AIN.of(conf.getAin()), Metadata.of(conf.getIcon(), ScenarioType.of(conf.getType())));
        break;
      case "settriggeractive": //$NON-NLS-1$
        api.setTriggerActive(AIN.of(conf.getAin()), conf.isActive());
        break;
      case "addcolorleveltemplate": //$NON-NLS-1$
        // TODO Optional: Hue, Saturation, Temperature
        // TODO AINs
        // api.addColorLevelTemplate(conf.getName(), Percent.of(conf.getPercent()), Hue.of(conf.getHue()), Saturation.of(conf.getSaturation()), TemperatureKelvin.of(conf.getTemperature()), conf.getColorPreset(), null); // TODO
        break;

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
        result = api.getDeviceListInfos().toString();
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
        final Quintet<SortedMap<UnixTimestamp, TemperatureCelsius>, SortedMap<UnixTimestamp, Percent>, SortedMap<UnixTimestamp, Voltage>, SortedMap<UnixTimestamp, Power>, SortedMap<UnixTimestamp, Energy>> devStats = api.getBasicDeviceStats(AIN.of(conf.getAin()));
        result = ""; // TODO XML?
        break;
      case "gettemplatelistinfos": //$NON-NLS-1$
        final List<Template> templInfos = api.getTemplateListInfos();
        result = ""; // TODO XML?
        break;
      case "getcolordefaults": //$NON-NLS-1$
        final Pair<List<Hs>, List<TemperatureKelvin>> colorDefaults = api.getColorDefaults();
        result = ""; // TODO XML?
        break;
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
        throw new IllegalArgumentException("Unsupported switchcmd: " + switchcmd); //$NON-NLS-1$
     }
    return result;
   }


  /**
   * Process.
   *
   * @param exchange Exchange
   * @throws Exception  Exception
   * @throws IllegalArgumentException Illegal or missing argument
   */
  @Override
  public void process(final Exchange exchange) throws Exception
   {
    final FBAHAConfiguration conf = endpoint.getConfiguration();
    final String switchcmd = conf.getSwitchcmd();
    if (!conf.parameterCheck())
     {
      throw new IllegalArgumentException("Missing arguments for switchcmd: " + switchcmd); //$NON-NLS-1$
     }
    final AHASessionMini api = endpoint.getApiProxy();
    final String result = executeSwitchCmd(conf, api, switchcmd);
    final Message msg = exchange.getIn();
    msg.setBody(result);
    exchange.setMessage(msg);
   }

 }
