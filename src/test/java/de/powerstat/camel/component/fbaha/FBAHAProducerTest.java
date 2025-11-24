/*
 * Copyright (C) 2022-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.camel.component.fbaha;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * FBAHA producer test.
 */
final class FBAHAProducerTest extends CamelTestSupport
 {
  /**
   * Default constructor.
   */
  /* default */ FBAHAProducerTest()
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
    try (FBAHAComponent component = new FBAHAComponent(context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint endpoint = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
       {
        try (FBAHAProducer test = new FBAHAProducer(endpoint))
         {
          assertNotNull(test, "test is null"); //$NON-NLS-1$
         }
       }
     }
   }


  /**
   * Process test.
   *
   * @throws IOException IO eception
   * @throws Exception Exception
   */
  @Test
  @Disabled("TODO")
  /* default */ void testProcess1() throws Exception
   {
    try (FBAHAComponent component = new FBAHAComponent(context))
     {
      final Map<String, Object> parameters = new ConcurrentHashMap<>();
      try (FBAHAEndpoint endpoint = (FBAHAEndpoint)component.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$ // TODO mock Component
       {
        try (FBAHAProducer test = new FBAHAProducer(endpoint))
         {
          final Exchange exchange = new DefaultExchange(context);
          test.process(exchange);
          final String body = (String)exchange.getMessage().getBody();
          assertEquals("", body, "body is not empty"); //$NON-NLS-1$ //$NON-NLS-2$
         }
       }
     }
   }

 }
