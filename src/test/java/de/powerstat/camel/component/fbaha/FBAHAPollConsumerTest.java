/*
 * Copyright (C) 2022-2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.Processor;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * Poll consumer tests.
 */
final class FBAHAPollConsumerTest extends CamelTestSupport
 {
  /**
   * Default constructor.
   */
  /* default */ FBAHAPollConsumerTest()
   {
    super();
   }


  /**
   * Constructor test.
   *
   * @throws Exception Exception
   */
  @Test
  @Disabled("TODO")
  /* default */ void testConstructor1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint endpoint = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
       {
        final Processor processor = null;
        try (FBAHAPollConsumer test = new FBAHAPollConsumer(endpoint, processor))
         {
          assertNotNull(test, "test is null"); //$NON-NLS-1$
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
  @Disabled("TODO")
  /* default */ void testPoll1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint endpoint = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
       {
        final Processor processor = null;
        try (FBAHAPollConsumer test = new FBAHAPollConsumer(endpoint, processor))
         {
          final int numOfMsgs = test.poll();
          assertEquals(0, numOfMsgs, "numOfMsgs != 0");
         }
       }
     }
   }

 }
