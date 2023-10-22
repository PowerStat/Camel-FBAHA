/*
 * Copyright (C) 2022-2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Consumer;
import org.apache.camel.Producer;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import de.powerstat.fb.mini.AHASessionMini;


/**
 * FBAHAEndpoint tests.
 */
public class FBAHAEndpointTest extends CamelTestSupport
 {
  /**
   * Constructor test.
   *
   * @throws Exception Exception
   */
  @Test
  @Disabled("TODO")
  public void constructor1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
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
  @Disabled("TODO")
  public void configuration1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$
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
  @Disabled("TODO")
  public void getApiProxy1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
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
  @Disabled("TODO")
  public void getApiProxy2() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
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
  @Disabled("TODO")
  public void createProducer1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
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
  @Disabled("TODO")
  public void createConsumer1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
       {
        try (Consumer consumerLocal = test.createConsumer(null))
         {
          assertNotNull(consumerLocal, "consumerLocal is null"); //$NON-NLS-1$
         }
       }
     }
   }

 }
