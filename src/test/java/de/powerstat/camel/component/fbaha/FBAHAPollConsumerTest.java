/*
 * Copyright (C) 2022 Dipl.-Inform. Kai Hofmann. All rigths reserved!
 */
package de.powerstat.camel.component.fbaha;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Processor;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * Poll consumer tests.
 */
public class FBAHAPollConsumerTest extends CamelTestSupport
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
      try (FBAHAEndpoint endpoint = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        final Processor processor = null;
        try (FBAHAPollConsumer test = new FBAHAPollConsumer(endpoint, processor))
         {
          assertNotNull(test);
         }
       }
     }
   }


  /**
   * Poll test.
   *
   * @throws IOException IO exception
   * @throws Exception Exception
   */
  @Test
  @Disabled
  public void poll1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (FBAHAEndpoint endpoint = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        endpoint.doStart(); // TODO mock AHASession
        final Processor processor = null;
        try (FBAHAPollConsumer test = new FBAHAPollConsumer(endpoint, processor))
         {
          final int numOfMsgs = test.poll();
          assertEquals(0, numOfMsgs);
         }
        endpoint.doStart();
       }
     }
   }

 }
