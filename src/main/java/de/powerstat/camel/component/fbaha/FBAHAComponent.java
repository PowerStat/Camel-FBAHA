/*
 * Copyright (C) 2022 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;


/**
 * FBAHA camel component.
 *
 * TODO Configuration
 */
@org.apache.camel.spi.annotations.Component("fbaha")
public class FBAHAComponent extends DefaultComponent
 {
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
   * Create endpoint.
   *
   * @param uri Endpoint uri
   * @param remaining Unused
   * @param parameters Endpoint parameters
   * @throws Exception Exception on failure
   */
  @Override
  protected Endpoint createEndpoint(final String uri, final String remaining, final Map<String, Object> parameters) throws Exception
   {
    final FBAHAConfiguration configuration = new FBAHAConfiguration();
    configuration.configure(remaining);
    final Endpoint endpoint = new FBAHAEndpoint(uri, this, configuration);
    setProperties(endpoint, parameters);
    return endpoint;
   }

 }
