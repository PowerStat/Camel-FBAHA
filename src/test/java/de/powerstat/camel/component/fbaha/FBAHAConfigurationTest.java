/*
 * Copyright (C) 2022-2025 Dipl.-Inform. Kai Hofmann. All rights reserved!
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements; and to You under the Apache License, Version 2.0.
 */
package de.powerstat.camel.component.fbaha;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;


/**
 * FBAHAConfiguration test.
 */
final class FBAHAConfigurationTest
 {
  /**
   * Test constant.
   */
  private static final String TEST = "test";

  /**
   * AIN constant.
   */
  private static final String AIN = "123456789012";

  /**
   * Get switch list constant.
   */
  private static final String GETSWITCHLIST = "getswitchlist";

  /**
   * Parameter check not true constant.
   */
  private static final String PARAMETER_CHECK_NOT_TRUE = "parameterCheck not true";


  /**
   * Default constructor.
   */
  /* default */ FBAHAConfigurationTest()
   {
    super();
   }


  /**
   * Test default constructor.
   */
  @Test
  /* default */ void testConstructor1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    assertNotNull(test, "test is null"); //$NON-NLS-1$
   }


  /**
   * Test hostname.
   */
  @Test
  /* default */ void testHostname1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setHostname(TEST);
    assertEquals(TEST, test.getHostname(), "hostname != 'test'"); //$NON-NLS-1$
   }


  /**
   * Test port.
   */
  @Test
  /* default */ void testPort1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setPort(443);
    assertEquals(443, test.getPort(), "port != 443"); //$NON-NLS-1$
   }


  /**
   * Test username.
   */
  @Test
  /* default */ void testUsername1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setUsername(TEST);
    assertEquals(TEST, test.getUsername(), "username != 'test'"); //$NON-NLS-1$
   }


  /**
   * Test password.
   */
  @Test
  /* default */ void testPassword1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setPassword("topSecret"); //$NON-NLS-1$
    assertEquals("topSecret", test.getPassword(), "password != 'topSecret'"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test ain.
   */
  @Test
  /* default */ void testAin1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    assertEquals(AIN, test.getAin(), "in != '123456789012'"); //$NON-NLS-1$
   }


  /**
   * Test temperature.
   */
  @Test
  /* default */ void testTemperature1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setTemperature(200);
    assertEquals(200, test.getTemperature(), "temperature != 200"); //$NON-NLS-1$
   }


  /**
   * Test onoff.
   */
  @Test
  /* default */ void testOnoff1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setOnoff(2);
    assertEquals(2, test.getOnoff(), "onoff != 2"); //$NON-NLS-1$
   }


  /**
   * Test level.
   */
  @Test
  /* default */ void testLevel1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setLevel(50);
    assertEquals(50, test.getLevel(), "evel != 50"); //$NON-NLS-1$
   }


  /**
   * Test hue.
   */
  @Test
  /* default */ void testHue1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setHue(180);
    assertEquals(180, test.getHue(), "hue != 180"); //$NON-NLS-1$
   }


  /**
   * Test saturation.
   */
  @Test
  /* default */ void testSaturation1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setSaturation(128);
    assertEquals(128, test.getSaturation(), "saturation != 128"); //$NON-NLS-1$
   }


  /**
   * Test duration.
   */
  @Test
  /* default */ void testDuration1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setDuration(1000);
    assertEquals(1000, test.getDuration(), "duration != 100"); //$NON-NLS-1$
   }


  /**
   * Test end timestamp.
   */
  @Test
  /* default */ void testEndtimestamp1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setEndtimestamp(1663251567);
    assertEquals(1663251567, test.getEndtimestamp(), "endtimestamp != 1663251567"); //$NON-NLS-1$
   }


  /**
   * Test target.
   */
  @Test
  /* default */ void testTarget1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setTarget("stop"); //$NON-NLS-1$
    assertEquals("stop", test.getTarget(), "target != stop"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test name.
   */
  @Test
  /* default */ void testName1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setName("testswitch"); //$NON-NLS-1$
    assertEquals("testswitch", test.getName(), "name != 'testswitch'"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test switchcmd.
   */
  @Test
  /* default */ void testSwitchcmd1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setSwitchcmd(GETSWITCHLIST);
    assertEquals(GETSWITCHLIST, test.getSwitchcmd(), "switchcmd != 'getswitchlist'"); //$NON-NLS-1$
   }


  /**
   * Test only on change.
   */
  @Test
  /* default */ void testOnlyOnChange1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setOnlyOnChange(true);
    assertTrue(test.isOnlyOnChange(), "onlyonchange != true"); //$NON-NLS-1$
   }


  /**
   * Test configure.
   *
   * @throws URISyntaxException URI syntax error
   */
  @Test
  /* default */ void testConfigure1() throws URISyntaxException
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    final String remaining = "test:topSecret@fritz.box:443/getswitchlist"; //$NON-NLS-1$
    test.configure(remaining);
    assertAll(
      () -> assertEquals(TEST, test.getUsername(), "username != 'test'"), //$NON-NLS-1$
      () -> assertEquals("topSecret", test.getPassword(), "password != 'topSecret'"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals("fritz.box", test.getHostname(), "hostname != 'fritz.bo'"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals(443, test.getPort(), "port != 443"), //$NON-NLS-1$
      () -> assertEquals(GETSWITCHLIST, test.getSwitchcmd(), "switchcmd != 'getswitchlist'") //$NON-NLS-1$
    );
   }


  /**
   * Test configure.
   *
   * @throws URISyntaxException URI syntax error
   */
  @Test
  /* default */ void testConfigure2() throws URISyntaxException
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    final String remaining = "test:topSecret@fritz.box:443"; //$NON-NLS-1$
    final Exception exception = assertThrows(
      URISyntaxException.class,
      () -> test.configure(remaining),
      "URI syntax exception expected" //$NON-NLS-1$
    );
    assertTrue(exception.getMessage().contains("No switchcmd found!"), "Msg not as expected"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test configure.
   *
   * @throws URISyntaxException URI syntax error
   */
  @Test
  /* default */ void testConfigure3() throws URISyntaxException
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    final String remaining = "/getswitchlist"; //$NON-NLS-1$
    test.configure(remaining);
    assertAll(
      () -> assertEquals("", test.getUsername(), "username not empty"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals("", test.getPassword(), "password not empty"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals("fritz.box", test.getHostname(), "hostname != 'fritz.box'"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals(443, test.getPort(), "port != 443"), //$NON-NLS-1$
      () -> assertEquals(GETSWITCHLIST, test.getSwitchcmd(), "switchcmd != 'getswitchlist'") //$NON-NLS-1$
    );
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setSwitchcmd("startulesubscription"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck2()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setSwitchcmd("setswitchon"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck3()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setEndtimestamp(0);
    test.setSwitchcmd("sethkrboost"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck4()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setTemperature(0);
    test.setSwitchcmd("sethkrtsoll"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck5()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setOnoff(0);
    test.setSwitchcmd("setsimpleonoff"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck6()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setLevel(0);
    test.setSwitchcmd("setlevel"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck7()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setLevel(0);
    test.setSwitchcmd("setlevelpercentage"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck8()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setHue(0);
    test.setSaturation(0);
    test.setDuration(0);
    test.setSwitchcmd("setcolor"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck9()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setTemperature(2700);
    test.setDuration(0);
    test.setSwitchcmd("setcolortemperature"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck10()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setTarget("stop"); //$NON-NLS-1$
    test.setSwitchcmd("setblind"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck11()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(AIN);
    test.setName(TEST);
    test.setSwitchcmd("setname"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), PARAMETER_CHECK_NOT_TRUE);
   }


  /**
   * Test parameterCheck.
   */
  @Test
  /* default */ void testParameterCheck12()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setSwitchcmd("unknowncmd"); //$NON-NLS-1$
    final Exception exception = assertThrows(
      IllegalArgumentException.class,
      () -> test.parameterCheck(),
      "Illegal argument exception expected" //$NON-NLS-1$
    );
    assertTrue(exception.getMessage().contains("Unsupported switchcmd: unknowncmd"), "Msg not as expected"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test toString.
   */
  @Test
  /* default */ void testToString1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin(""); //$NON-NLS-1$
    test.setDuration(0);
    test.setEndtimestamp(0);
    test.setHostname(""); //$NON-NLS-1$
    test.setHue(0);
    test.setLevel(0);
    test.setName(""); //$NON-NLS-1$
    test.setOnlyOnChange(false);
    test.setOnoff(0);
    test.setPassword(""); //$NON-NLS-1$
    test.setPort(0);
    test.setSaturation(0);
    test.setSwitchcmd(""); //$NON-NLS-1$
    test.setTarget(""); //$NON-NLS-1$
    test.setTemperature(0);
    test.setUsername(""); //$NON-NLS-1$
    assertEquals("FBAHAConfiguration[hostname=, port=0, username=, password=XXX, switchcmd=, ain=, temperature=0, onoff=0, level=0, hue=0, saturation=0, duration=0, endtimestamp=0, target=, name=, active=false, colorpreset=false, icon=0, percent=0, type=null, onlyonchange=false]", test.toString(), "toString not as expected"); //$NON-NLS-1$
   }

 }
