/*
 * Copyright (C) 2022 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;


/**
 * FBAHA component tests.
 */
public class FBAHAComponentTest extends CamelTestSupport
 {
  /**
   * Default constructor.
   */
  public FBAHAComponentTest()
   {
    super();
   }


  /**
   * Test default constructor.
   *
   * @throws IOException IO exception
   */
  @Test
  public void constructor1() throws IOException
   {
    try (FBAHAComponent test = new FBAHAComponent())
     {
      assertNotNull(test);
     }
   }


  /**
   * Test constructor with context.
   *
   * @throws IOException IO exception
   */
  @Test
  public void constructor2() throws IOException
   {
    try (FBAHAComponent test = new FBAHAComponent(this.context))
     {
      assertNotNull(test);
     }
   }


  /**
   * Test create endpoint.
   *
   * @throws IOException IO exception
   * @throws Exception Exception
   */
  @Test
  public void createEndpoint1() throws Exception
   {
    try (FBAHAComponent test = new FBAHAComponent(this.context))
     {
      final Map<String, Object> parameters = new HashMap<>();
      try (Endpoint endpoint = test.createEndpoint("fbaha::topSecret@/getswitchlist", ":topSecret@/getswitchlist", parameters)) //$NON-NLS-1$ //$NON-NLS-2$
       {
        assertNotNull(endpoint);
       }
     }
   }


  // TODO Consumer tests
  // TODO Producer tests


  /**
   * Create route builder.
   */
  /*
  @Override
  protected RouteBuilder createRouteBuilder() throws Exception
   {
    return new RouteBuilder()
     {
      /**
       * Configure.
       * /
      @Override
      public void configure()
       {
        from("fbaha://foo").to("fbaha://bar").to("mock:result");
       }

     };
   }
  */

 }
