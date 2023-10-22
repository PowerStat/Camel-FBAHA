/*
 * Copyright (C) 2022-2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.support.DefaultProducer;
import org.xml.sax.SAXException;

import de.powerstat.fb.mini.AHASessionMini;
import de.powerstat.fb.mini.AHASessionMini.HandleBlind;
import de.powerstat.fb.mini.AIN;
import de.powerstat.fb.mini.Temperature;
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
    String result = "";
    switch (switchcmd)
     {
      case "setswitchon": //$NON-NLS-1$
        result = String.valueOf(api.setSwitchOn(AIN.of(conf.getAin())));
        break;
      case "setswitchoff": //$NON-NLS-1$
        result =  String.valueOf(api.setSwitchOff(AIN.of(conf.getAin())));
        break;
      case "setswitchtoggle": //$NON-NLS-1$
        result =  String.valueOf(api.setSwitchToggle(AIN.of(conf.getAin())));
        break;
      case "sethkrtsoll": //$NON-NLS-1$
        api.setHkrtSoll(AIN.of(conf.getAin()), Temperature.of(conf.getTemperature()));
        break;
      case "applytemplate": //$NON-NLS-1$
        api.applyTemplate(conf.getAin());
        break;
      case "setsimpleonoff": //$NON-NLS-1$
        api.setSimpleOnOff(AIN.of(conf.getAin()), conf.getOnoff());
        break;
      case "setlevel": //$NON-NLS-1$
        api.setLevel(AIN.of(conf.getAin()), conf.getLevel());
        break;
      case "setlevelpercentage": //$NON-NLS-1$
        api.setLevelPercentage(AIN.of(conf.getAin()), conf.getLevel());
        break;
      case "setcolor": //$NON-NLS-1$
        api.setColor(AIN.of(conf.getAin()), conf.getHue(), conf.getSaturation(), conf.getDuration());
        break;
      case "setcolortemperature": //$NON-NLS-1$
        api.setColorTemperature(AIN.of(conf.getAin()), (int)conf.getTemperature(), conf.getDuration());
        break;
      case "sethkrboost": //$NON-NLS-1$
        result = String.valueOf(api.setHkrBoost(AIN.of(conf.getAin()), conf.getEndtimestamp()));
        break;
      case "sethkrwindowopen": //$NON-NLS-1$
        result = String.valueOf(api.setHkrWindowOpen(AIN.of(conf.getAin()), conf.getEndtimestamp()));
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
        result = XMLHelper.convertDocumentToString(api.getDeviceListInfos());
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
        result = XMLHelper.convertDocumentToString(api.getBasicDeviceStats(AIN.of(conf.getAin())));
        break;
      case "gettemplatelistinfos": //$NON-NLS-1$
        result = XMLHelper.convertDocumentToString(api.getTemplateListInfos());
        break;
      case "getcolordefaults": //$NON-NLS-1$
        result = XMLHelper.convertDocumentToString(api.getColorDefaults());
        break;
      case "getsubscriptionstate": //$NON-NLS-1$
        result = XMLHelper.convertDocumentToString(api.getSubscriptionState());
        break;
      case "getdeviceinfo": //$NON-NLS-1$
        result = XMLHelper.convertDocumentToString(api.getDeviceInfo(AIN.of(conf.getAin())));
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
    final FBAHAConfiguration conf = this.endpoint.getConfiguration();
    final String switchcmd = conf.getSwitchcmd();
    if (!conf.parameterCheck())
     {
      throw new IllegalArgumentException("Missing arguments for switchcmd: " + switchcmd); //$NON-NLS-1$
     }
    final AHASessionMini api = this.endpoint.getApiProxy();
    final String result = executeSwitchCmd(conf, api, switchcmd);
    final Message msg = exchange.getIn();
    msg.setBody(result);
    exchange.setMessage(msg);
   }

 }
