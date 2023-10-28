/*
 * Copyright (C) 2022-2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.camel.Category;
import org.apache.camel.Component;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.support.ScheduledPollEndpoint;
import org.apache.camel.util.ObjectHelper;
import org.xml.sax.SAXException;

import de.powerstat.fb.mini.AHASessionMini;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


/**
 * FBAHA endpoint.
 */
@SuppressWarnings({"java:S1160", "java:S2160"})
@UriEndpoint(firstVersion = "3.18.1", scheme = "fbaha", title = "FBAHA", syntax = "fbaha:hostname:port/switchcmd", alternativeSyntax = "fbaha:username:password@hostname:port/switchcmd", category = {Category.API, Category.IOT, Category.MANAGEMENT, Category.MONITORING, Category.NETWORKING})
public class FBAHAEndpoint extends ScheduledPollEndpoint
 {
  /**
   * FB AHA configuration.
   */
  @UriParam
  @Metadata(required = true)
  private FBAHAConfiguration configuration;


  /**
   * Default constructor.
   */
  /*
  public FBAHAEndpoint()
   {
    super();
    this.configuration = new FBAHAConfiguration();
    this.configuration.setHostname("fritz.box"); //$NON-NLS-1$
    this.configuration.setPort(443);
   }
   */


  /**
   * Constructor.
   *
   * @param uri Endpoint uri
   * @param component FBAHA component
   */
  /*
  public FBAHAEndpoint(final String uri, final FBAHAComponent component)
   {
    super(uri, component);
   }
  */


  /**
   * Constructor.
   *
   * @param uri Endpoint uri fbaha:user:password@hostname:port/switchcommand?options
   * @param component FBAHA component
   * @param configuration Configuration
   */
  @SuppressFBWarnings({"EI_EXPOSE_REP2", "FCCD_FIND_CLASS_CIRCULAR_DEPENDENCY"})
  public FBAHAEndpoint(final String uri, final Component component, final FBAHAConfiguration configuration)
   {
    super(uri, component);
    this.configuration = configuration;
   }


  /*
  @Override
  public boolean isSingleton()
   {
    return false; // TODO singleton per hostname?
   }
  */



  /**
   * Create producer.
   *
   * @throws Exception Exception
   */
  @Override
  public Producer createProducer() throws Exception
   {
    ObjectHelper.notNull(this.configuration, "configuration"); //$NON-NLS-1$
    return new FBAHAProducer(this);
   }


  /**
   * Create consumer.
   *
   * @throws Exception Exception
   */
  @Override
  public Consumer createConsumer(final Processor processor) throws Exception
   {
    ObjectHelper.notNull(this.configuration, "configuration"); //$NON-NLS-1$
    final Consumer consumer = new FBAHAPollConsumer(this, processor);
    configureConsumer(consumer);
    return consumer;
   }


  /**
   * Get configuration.
   *
   * @return FBAHAConfiguration
   */
  @SuppressFBWarnings({"EI_EXPOSE_REP"})
  public FBAHAConfiguration getConfiguration()
   {
    return this.configuration;
   }


  /**
   * Set configuration.
   *
   * @param configuration Configuration
   * @throws ParserConfigurationException  Parser configuration exception
   * @throws KeyStoreException  Key store exception
   * @throws NoSuchAlgorithmException  No such algorithm exception
   * @throws KeyManagementException  Key management exception
   * @throws SAXException SAX exception
   * @throws IOException IO exception
   * @throws InvalidKeyException Invalid key exception
   */
  @SuppressFBWarnings({"EI_EXPOSE_REP2"})
  public void setConfiguration(final FBAHAConfiguration configuration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ParserConfigurationException, IOException, SAXException, InvalidKeyException
   {
    // TODO synchronize
    boolean afterDoStart = false;
    if (this.configuration.getApiProxy().hasValidSession())
     {
      this.configuration.getApiProxy().logoff();
      afterDoStart = true;
     }
    this.configuration = configuration;
    if (afterDoStart)
     {
      this.configuration.getApiProxy().logon();
     }
   }


  /**
   * Get api proxy.
   *
   * @return Api proxy
   */
  @SuppressFBWarnings({"EI_EXPOSE_REP"})
  public AHASessionMini getApiProxy()
   {
    // TODO synchronize
    return this.configuration.getApiProxy();
   }

 }
