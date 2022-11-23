/*
 * Copyright (C) 2022 Dipl.-Inform. Kai Hofmann. All rigths reserved!
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
 *
 */
public class FBAHAConfigurationTest
 {
  /**
   * Test default constructor.
   */
  @Test
  public void constructor1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    assertNotNull(test, "test is null"); //$NON-NLS-1$
   }


  /**
   * Test hostname.
   */
  @Test
  public void hostname1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setHostname("test"); //$NON-NLS-1$
    assertEquals("test", test.getHostname(), "hostname != 'test'"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test port.
   */
  @Test
  public void port1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setPort(443);
    assertEquals(443, test.getPort(), "port != 443"); //$NON-NLS-1$
   }


  /**
   * Test username.
   */
  @Test
  public void username1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setUsername("test"); //$NON-NLS-1$
    assertEquals("test", test.getUsername(), "username != 'test'"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test password.
   */
  @Test
  public void password1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setPassword("topSecret"); //$NON-NLS-1$
    assertEquals("topSecret", test.getPassword(), "password != 'topSecret'"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test ain.
   */
  @Test
  public void ain1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    assertEquals("123456789012", test.getAin(), "in != '123456789012'"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test temperature.
   */
  @Test
  public void temperature1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setTemperature(200);
    assertEquals(200, test.getTemperature(), "temperature != 200"); //$NON-NLS-1$
   }


  /**
   * Test onoff.
   */
  @Test
  public void onoff1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setOnoff(2);
    assertEquals(2, test.getOnoff(), "onoff != 2"); //$NON-NLS-1$
   }


  /**
   * Test level.
   */
  @Test
  public void level1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setLevel(50);
    assertEquals(50, test.getLevel(), "evel != 50"); //$NON-NLS-1$
   }


  /**
   * Test hue.
   */
  @Test
  public void hue1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setHue(180);
    assertEquals(180, test.getHue(), "hue != 180"); //$NON-NLS-1$
   }


  /**
   * Test saturation.
   */
  @Test
  public void saturation1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setSaturation(128);
    assertEquals(128, test.getSaturation(), "saturation != 128"); //$NON-NLS-1$
   }


  /**
   * Test duration.
   */
  @Test
  public void duration1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setDuration(1000);
    assertEquals(1000, test.getDuration(), "duration != 100"); //$NON-NLS-1$
   }


  /**
   * Test end timestamp.
   */
  @Test
  public void endtimestamp1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setEndtimestamp(1663251567);
    assertEquals(1663251567, test.getEndtimestamp(), "endtimestamp != 1663251567"); //$NON-NLS-1$
   }


  /**
   * Test target.
   */
  @Test
  public void target1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setTarget("stop"); //$NON-NLS-1$
    assertEquals("stop", test.getTarget(), "target != stop"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test name.
   */
  @Test
  public void name1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setName("testswitch"); //$NON-NLS-1$
    assertEquals("testswitch", test.getName(), "name != 'testswitch'"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test switchcmd.
   */
  @Test
  public void switchcmd1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setSwitchcmd("getswitchlist"); //$NON-NLS-1$
    assertEquals("getswitchlist", test.getSwitchcmd(), "switchcmd != 'getswitchlist'"); //$NON-NLS-1$ //$NON-NLS-2$
   }


  /**
   * Test only on change.
   */
  @Test
  public void onlyOnChange1()
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
  public void configure1() throws URISyntaxException
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    final String remaining = "test:topSecret@fritz.box:443/getswitchlist"; //$NON-NLS-1$
    test.configure(remaining);
    assertAll(
      () -> assertEquals("test", test.getUsername(), "username != 'test'"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals("topSecret", test.getPassword(), "password != 'topSecret'"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals("fritz.box", test.getHostname(), "hostname != 'fritz.bo'"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals(443, test.getPort(), "port != 443"), //$NON-NLS-1$
      () -> assertEquals("getswitchlist", test.getSwitchcmd(), "switchcmd != 'getswitchlist'") //$NON-NLS-1$ //$NON-NLS-2$
    );
   }


  /**
   * Test configure.
   *
   * @throws URISyntaxException URI syntax error
   */
  @Test
  public void configure2() throws URISyntaxException
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
  public void configure3() throws URISyntaxException
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    final String remaining = "/getswitchlist"; //$NON-NLS-1$
    test.configure(remaining);
    assertAll(
      () -> assertEquals("", test.getUsername(), "username not empty"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals("", test.getPassword(), "password not empty"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals("fritz.box", test.getHostname(), "hostname != 'fritz.box'"), //$NON-NLS-1$ //$NON-NLS-2$
      () -> assertEquals(443, test.getPort(), "port != 443"), //$NON-NLS-1$
      () -> assertEquals("getswitchlist", test.getSwitchcmd(), "switchcmd != 'getswitchlist'") //$NON-NLS-1$ //$NON-NLS-2$
    );
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck1()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setSwitchcmd("startulesubscription"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck2()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setSwitchcmd("setswitchon"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck3()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setEndtimestamp(0);
    test.setSwitchcmd("sethkrboost"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck4()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setTemperature(0);
    test.setSwitchcmd("sethkrtsoll"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck5()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setOnoff(0);
    test.setSwitchcmd("setsimpleonoff"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck6()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setLevel(0);
    test.setSwitchcmd("setlevel"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck7()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setLevel(0);
    test.setSwitchcmd("setlevelpercentage"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck8()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setHue(0);
    test.setSaturation(0);
    test.setDuration(0);
    test.setSwitchcmd("setcolor"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck9()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setTemperature(2700);
    test.setDuration(0);
    test.setSwitchcmd("setcolortemperature"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck10()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setTarget("stop"); //$NON-NLS-1$
    test.setSwitchcmd("setblind"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck11()
   {
    final FBAHAConfiguration test = new FBAHAConfiguration();
    test.setAin("123456789012"); //$NON-NLS-1$
    test.setName("test"); //$NON-NLS-1$
    test.setSwitchcmd("setname"); //$NON-NLS-1$
    assertTrue(test.parameterCheck(), "parameterCheck not true"); //$NON-NLS-1$
   }


  /**
   * Test parameterCheck.
   */
  @Test
  public void parameterCheck12()
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
  public void toString1()
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
    assertEquals("FBAHAConfiguration[hostname=, port=0, username=, password=XXX, switchcmd=, ain=, temperature=0, onoff=0, level=0, hue=0, saturation=0, duration=0, endtimestamp=0, target=, name=, onlyonchange=false]", test.toString(), "toString not as expected"); //$NON-NLS-1$
   }

 }
