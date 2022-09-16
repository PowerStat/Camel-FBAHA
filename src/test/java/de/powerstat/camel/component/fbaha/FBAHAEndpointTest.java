/*
 * Copyright (C) 2022 Dipl.-Inform. Kai Hofmann. All rigths reserved!
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
  public void constructor1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        assertNotNull(test);
       }
     }
   }


  /**
   * configuration test.
   *
   * @throws Exception Exception
   */
  @Test
  public void configuration1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        final FBAHAConfiguration configuration2 = new FBAHAConfiguration();
        configuration2.setHostname("fritz.box");
        configuration2.setPort(443);
        configuration2.setUsername("");
        configuration2.setPassword("topSecret");
        configuration2.setSwitchcmd("getswitchlist"); //$NON-NLS-1$
        test.setConfiguration(configuration2);
        final FBAHAConfiguration conf = test.getConfiguration();
        assertEquals(configuration2, conf);
       }
     }
   }


  /**
   * Get api proxy test.
   *
   * @throws Exception Exception
   */
  @Test
  public void getApiProxy1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        final AHASessionMini api = test.getApiProxy();
        assertNotNull(api);
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
  @Disabled
  public void getApiProxy2() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        test.doStart(); // TODO mock AHASession
        final AHASessionMini api = test.getApiProxy();
        assertNotNull(api);
        test.doStop();
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
  public void createProducer1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        try (Producer producer = test.createProducer())
         {
          assertNotNull(producer);
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
  public void createConsumer1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint test = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        try (Consumer consumerLocal = test.createConsumer(null))
         {
          assertNotNull(consumerLocal);
         }
       }
     }
   }

 }
