/*
 * Copyright (C) 2022 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;
import org.xml.sax.SAXException;

import de.powerstat.fb.mini.AHASessionMini;


/**
 * FBAHA camel component.
 *
 * TODO Configuration
 */
@org.apache.camel.spi.annotations.Component("fbaha")
public class FBAHAComponent extends DefaultComponent
 {
  /**
   * API proxy cache.
   *
   * key = "hostname:port:username"
   * value = AHASessionMini
   */
  Map<String, AHASessionMini> apiProxyCache = new ConcurrentHashMap<>();


  /**
   * Default constructor.
   */
  public FBAHAComponent()
   {
    super();
   }


  /**
   * Constructor.
   *
   * @param context Camel context
   */
  public FBAHAComponent(final CamelContext context)
   {
    super(context);
   }


  /**
   * Configure api proxy into configuration.
   *
   * @param configuration Configuration
   * @throws KeyManagementException Key management exception
   * @throws NoSuchAlgorithmException No such algorithm exception
   * @throws KeyStoreException Key store exception
   * @throws ParserConfigurationException Parser configuration exception
   * @throws SAXException  SAX exception
   * @throws IOException IO exception
   * @throws InvalidKeyException Invalid key exception
   */
  private void configureApiProxy(final FBAHAConfiguration configuration) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, ParserConfigurationException, InvalidKeyException, IOException, SAXException
   {
    final String key = configuration.getHostname() + ":" + configuration.getPort() + ":" + configuration.getUsername(); //$NON-NLS-1$ //$NON-NLS-2$
    synchronized (this)
     {
      AHASessionMini apiProxy = this.apiProxyCache.get(key);
      if (apiProxy == null)
       {
        apiProxy = AHASessionMini.newInstance(configuration.getHostname(), configuration.getPort(), configuration.getUsername(), configuration.getPassword());
        this.apiProxyCache.put(key, apiProxy);
        if (!apiProxy.logon())
         {
          throw new IllegalStateException("Could not login to: " + configuration.getHostname()); //$NON-NLS-1$
         }
       }
      configuration.setApiProxy(apiProxy);
     }
   }


  /**
   * Create endpoint.
   *
   * @param uri Endpoint uri
   * @param remaining Unused
   * @param parameters Endpoint parameters
   * @throws Exception Exception on failure
   */
  @Override
  public Endpoint createEndpoint(final String uri, final String remaining, final Map<String, Object> parameters) throws Exception
   {
    final FBAHAConfiguration configuration = new FBAHAConfiguration();
    configuration.configure(remaining);
    configureApiProxy(configuration);
    configuration.initParameters(parameters);
    final Endpoint endpoint = new FBAHAEndpoint(uri, this, configuration);
    setProperties(endpoint, parameters);
    return endpoint;
   }


  /**
   * Do stop.
   */
  @Override
  public void doStop() throws Exception
   {
    for (final Map.Entry<String, AHASessionMini> entry : this.apiProxyCache.entrySet())
     {
      if (!entry.getValue().logoff())
       {
        // TODO log("Could not logoff from: " + entry.getKey()); //$NON-NLS-1$
       }

     }
   }

 }
