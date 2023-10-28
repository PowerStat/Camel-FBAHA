/*
 * Copyright (C) 2022-2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.Consumer;
import org.apache.camel.Producer;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.powerstat.fb.mini.AHASessionMini;


/**
 * FBAHAEndpoint tests.
 */
final class FBAHAEndpointTest extends CamelTestSupport
 {
  /**
   * TODO constant.
   */
  private static final String TODO = "TODO";

  /**
   * FBAHA top secret get switch list constant.
   */
  private static final String FBAHA_TOP_SECRET_GETSWITCHLIST = "fbaha::topSecret@/getswitchlist";

  /**
   * Get switch list constant.
   */
  private static final String TOP_SECRET_GETSWITCHLIST = ":topSecret@/getswitchlist";


  /**
   * Default constructor.
   */
  /* default */ FBAHAEndpointTest()
   {
    super();
   }


  /**
   * Constructor test.
   *
   * @throws Exception Exception
   */
  @Test
  @Disabled(TODO)
  /* default */ void testConstructor1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint(FBAHA_TOP_SECRET_GETSWITCHLIST, TOP_SECRET_GETSWITCHLIST, parameters)) // TODO mock Component
       {
        assertNotNull(test, "test == null"); //$NON-NLS-1$
       }
     }
   }


  /**
   * configuration test.
   *
   * @throws Exception Exception
   */
  @Test
  @Disabled(TODO)
  /* default */ void testConfiguration1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint(FBAHA_TOP_SECRET_GETSWITCHLIST, TOP_SECRET_GETSWITCHLIST, parameters))
       {
        final FBAHAConfiguration configuration2 = new FBAHAConfiguration();
        configuration2.setHostname("fritz.box"); //$NON-NLS-1$
        configuration2.setPort(443);
        configuration2.setUsername(""); //$NON-NLS-1$
        configuration2.setPassword("topSecret"); //$NON-NLS-1$
        configuration2.setSwitchcmd("getswitchlist"); //$NON-NLS-1$
        test.setConfiguration(configuration2);
        final FBAHAConfiguration conf = test.getConfiguration();
        assertEquals(configuration2, conf, "conf != configuration2"); //$NON-NLS-1$
       }
     }
   }


  /**
   * Get api proxy test.
   *
   * @throws Exception Exception
   */
  @Test
  @Disabled(TODO)
  /* default */ void testGetApiProxy1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint(FBAHA_TOP_SECRET_GETSWITCHLIST, TOP_SECRET_GETSWITCHLIST, parameters)) // TODO mock Component
       {
        final AHASessionMini api = test.getApiProxy();
        assertNotNull(api, "api is null"); //$NON-NLS-1$
       }
     }
   }


  /**
   * Get api proxy test.
   *
   * @throws IOException IO exception
   * @throws Exception Exception
   */
  @Test
  @Disabled(TODO)
  /* default */ void testGetApiProxy2() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint(FBAHA_TOP_SECRET_GETSWITCHLIST, TOP_SECRET_GETSWITCHLIST, parameters)) // TODO mock Component
       {
        final AHASessionMini api = test.getApiProxy();
        assertNotNull(api, "api is null"); //$NON-NLS-1$
       }
     }
   }


  /**
   * Create producer test.
   *
   * @throws IOException IO exception
   * @throws Exception Exception
   */
  @Test
  @Disabled(TODO)
  /* default */ void testCreateProducer1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint(FBAHA_TOP_SECRET_GETSWITCHLIST, TOP_SECRET_GETSWITCHLIST, parameters)) // TODO mock Component
       {
        try (Producer producer = test.createProducer())
         {
          assertNotNull(producer, "producer is null"); //$NON-NLS-1$
         }
       }
     }
   }


  /**
   * Create consumer test.
   *
   * @throws IOException IO exception
   * @throws Exception Exception
   */
  @Test
  @Disabled(TODO)
  /* default */ void testCreateConsumer1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint(FBAHA_TOP_SECRET_GETSWITCHLIST, TOP_SECRET_GETSWITCHLIST, parameters)) // TODO mock Component
       {
        try (Consumer consumerLocal = test.createConsumer(null))
         {
          assertNotNull(consumerLocal, "consumerLocal is null"); //$NON-NLS-1$
         }
       }
     }
   }

 }
