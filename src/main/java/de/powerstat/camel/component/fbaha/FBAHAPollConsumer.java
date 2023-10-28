/*
 * Copyright (C) 2022-2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import java.io.IOException;
import java.util.List;
import java.util.Map;
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
import org.xml.sax.SAXException;

import de.powerstat.fb.mini.AHASessionMini;
import de.powerstat.fb.mini.AIN;
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
    this.context =  defaultEndpoint.getCamelContext();
    this.endpoint = ((FBAHAEndpoint)defaultEndpoint);
    this.conf =  this.endpoint.getConfiguration();
   }


  /**
   * Switchcmd with ain result cache handler.
   *
   * @param switchcmdAin Switchcmd with ain if one
   * @param result Result from actual call.
   * @return Result or null if conf.onlyOnChange is true, otherwise always result
   */
  private String cacheHandler(final String switchcmdAin, final String result)
   {
    if (this.conf.isOnlyOnChange() && (result != null))
     {
      final String lastResult = this.resultCache.get(switchcmdAin);
      if ((lastResult != null) && lastResult.equals(result))
       {
        return null;
       }
      this.resultCache.put(switchcmdAin, result);
     }
    return result;
   }


  /**
   * Execute switch command.
   *
   * @param switchcmd Swith command
   * @return Result of switch command
   * @throws TransformerFactoryConfigurationError Transformer factory configuration error
   */
  @SuppressFBWarnings({"CC_CYCLOMATIC_COMPLEXITY", "FII_USE_METHOD_REFERENCE"})
  private String executeSwitchCmd(final String switchcmd) throws TransformerFactoryConfigurationError
   {
    var result = ""; //$NON-NLS-1$
    try
     {
      switch (switchcmd)
       {
        case "getswitchlist": //$NON-NLS-1$
          final List<AIN> switches = this.api.getSwitchList();
          result = switches.stream().map(AIN::stringValue).collect(Collectors.joining(", ")); //$NON-NLS-1$
          break;
        case "getswitchstate": //$NON-NLS-1$
          result = String.valueOf(this.api.getSwitchState(AIN.of(this.conf.getAin())));
          break;
        case "getswitchpresent": //$NON-NLS-1$
          result = String.valueOf(this.api.isSwitchPresent(AIN.of(this.conf.getAin())));
          break;
        case "getswitchpower": //$NON-NLS-1$
          result = String.valueOf(this.api.getSwitchPower(AIN.of(this.conf.getAin())).getPowerWatt());
          break;
        case "getswitchenergy": //$NON-NLS-1$
          result = String.valueOf(this.api.getSwitchEnergy(AIN.of(this.conf.getAin())).longValue());
          break;
        case "getswitchname": //$NON-NLS-1$
          result = this.api.getSwitchName(AIN.of(this.conf.getAin()));
          break;
        case "getdevicelistinfos": //$NON-NLS-1$
          result = XMLHelper.convertDocumentToString(this.api.getDeviceListInfos());
          break;
        case "gettemperature": //$NON-NLS-1$
          result = String.valueOf(this.api.getTemperature(AIN.of(this.conf.getAin())).getTemperatureCelsius());
          break;
        case "gethkrtsoll": //$NON-NLS-1$
          result = String.valueOf(this.api.getHkrtSoll(AIN.of(this.conf.getAin())).getTemperatureCelsius());
          break;
        case "gethkrkomfort": //$NON-NLS-1$
          result = String.valueOf(this.api.getHkrKomfort(AIN.of(this.conf.getAin())).getTemperatureCelsius());
          break;
        case "gethkrabsenk": //$NON-NLS-1$
          result = String.valueOf(this.api.getHkrAbsenk(AIN.of(this.conf.getAin())).getTemperatureCelsius());
          break;
        case "getbasicdevicestats": //$NON-NLS-1$
          result = XMLHelper.convertDocumentToString(this.api.getBasicDeviceStats(AIN.of(this.conf.getAin())));
          break;
        case "gettemplatelistinfos": //$NON-NLS-1$
          result = XMLHelper.convertDocumentToString(this.api.getTemplateListInfos());
          break;
        case "getcolordefaults": //$NON-NLS-1$
          result = XMLHelper.convertDocumentToString(this.api.getColorDefaults());
          break;
        case "getsubscriptionstate": //$NON-NLS-1$
          result = XMLHelper.convertDocumentToString(this.api.getSubscriptionState());
          break;
        case "getdeviceinfo": //$NON-NLS-1$
          result = XMLHelper.convertDocumentToString(this.api.getDeviceInfo(AIN.of(this.conf.getAin())));
          break;
        default:
          result = null;
       }
     }
    catch (final IOException | SAXException | TransformerException | TransformerFactoryConfigurationError e)
     {
      result = null;
     }
    return cacheHandler(switchcmd + (this.conf.getAin() == null ? "" : this.conf.getAin()), result); //$NON-NLS-1$
   }


  /**
   * Handle switch cmd.
   *
   * @return Exchange with result.
   * @throws TransformerFactoryConfigurationError Transformer factory configuration error
   * @throws IllegalArgumentException Illegal or missing argument
   */
  private Exchange handleSwitchCmd() throws TransformerFactoryConfigurationError
   {
    final String switchcmd = this.conf.getSwitchcmd();
    if (!this.conf.parameterCheck())
     {
      throw new IllegalArgumentException("Missing arguments for switchcmd: " + switchcmd); //$NON-NLS-1$
     }
    final String result = executeSwitchCmd(switchcmd);
    if (result == null)
     {
      return null;
     }
    final Exchange exchange = new DefaultExchange(this.context);
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
    this.api = this.endpoint.getApiProxy();
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
