/*
 * Copyright (C) 2022-2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Map;

import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.spi.UriPath;

import de.powerstat.fb.mini.AHASessionMini;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


/**
 * FB AHA configuration.
 *
 * TODO Parameter check
 */
@UriParams
public class FBAHAConfiguration
 {
  /**
   * FB AHA api proxy.
   */
  private AHASessionMini apiProxy;

  /**
   * FB hostname.
   */
  @UriPath
  @Metadata(required = false, defaultValue = "fritz.box", description = "FritzBox hostname to connect to.")
  private String hostname; // TODO Hostname

  /**
   * FB port.
   */
  @UriPath
  @Metadata(required = false, defaultValue = "443", description = "FritzBox port to connect to.")
  private int port; // TODO Port

  /**
   * FB username.
   */
  @UriPath
  @Metadata(required = false, description = "FritzBox username if required for login.")
  private String username; // TODO Username

  /**
   * FB password.
   */
  @UriPath(secret = true)
  @Metadata(required = false, description = "FritzBox password for login.")
  private String password; // TODO Password

  /**
   * Switch command.
   */
  @UriPath(description = "Switch command", enums = "setswitchon, setswitchoff, setswitchtoggle, sethkrtsoll, applytemplate, setsimpleonoff, setlevel, setlevelpercentage, setcolor, setcolortemperature, sethkrboost, sethkrwindowopen, setblind, setname, startulesubscription, getswitchlist, getswitchstate, getswitchpresent, getswitchpower, getswitchenergy, getswitchname, getdevicelistinfos, gettemperature, gethkrtsoll, gethkrkomfort, gethkrabsenk, getbasicdevicestats, gettemplatelistinfos, getcolordefaults, getsubscriptionstate, getdeviceinfo")
  @Metadata(required = true)
  private String switchcmd; // TODO Change to enum type

  /**
   * Actor identification number.
   */
  @UriParam
  @Metadata(required = false, description = "Actor identification number.")
  private String ain; // TODO AIN

  /**
   * Temperature in deci celsius or in kelvin 2700-6500.
   */
  @UriParam
  @Metadata(required = false, description = "deci celsius temperature or kelvin 2700-6500.")
  private long temperature; // TODO Temperature

  /**
   * on, off, toggle.
   */
  @UriParam
  @Metadata(required = false, description = "0 = off, 1 = on, 2 = toggle.")
  private int onoff; // TODO OnOffToggle

  /**
   * Level 0-100%.
   */
  @UriParam
  @Metadata(required = false, description = "0-100%")
  private int level; // TODO Percentage

  /**
   * Hue 0-359.
   */
  @UriParam
  @Metadata(required = false, description = "0-359")
  private int hue; // TODO Hue

  /**
   * Saturation 0-255.
   */
  @UriParam
  @Metadata(required = false, description = "0-255")
  private int saturation; // TODO Saturation

  /**
   * Duration in milliseconds.
   */
  @UriParam
  @Metadata(required = false, description = "milliseconds")
  private int duration; // TODO duration

  /**
   * Endtimestamp in seconds since 1970.
   */
  @UriParam
  @Metadata(required = false, description = "Seconds since 1970-01-01, max 24 hours in the future.")
  private long endtimestamp; // TODO TimeStamp

  /**
   * Target: open, close, stop.
   */
  @UriParam
  @Metadata(required = false, description = "open, close or stop")
  private String target; // TODO enum

  /**
   * Device or group name.
   */
  @UriParam
  @Metadata(required = false, description = "Device or groupname, max 40 characters")
  private String name; // TODO DeviceOrGroupName

  /**
   * Consumer only: send exchange only on change. Defaults to false.
   */
  @UriParam
  @Metadata(required = false, description = "Consumer only: send exchange only on change")
  private boolean onlyOnChange;


  /**
   * Default constructor.
   */
  public FBAHAConfiguration()
   {
    super();
   }


  /**
   * Initialize parameters from uri.
   *
   * @param parameters URI parameters
   */
  public void initParameters(final Map<String, Object> parameters)
   {
    Object obj = parameters.remove("ain"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      ain = value;
     }
    obj = parameters.remove("name"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      name = value;
     }
    obj = parameters.remove("target"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      target = value;
     }
    obj = parameters.remove("temperature"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      temperature = Long.parseLong(value);
     }
    obj = parameters.remove("endtimestamp"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      endtimestamp = Long.parseLong(value);
     }
    obj = parameters.remove("onoff"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      onoff = Integer.parseInt(value);
     }
    obj = parameters.remove("level"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      level = Integer.parseInt(value);
     }
    obj = parameters.remove("hue"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      hue = Integer.parseInt(value);
     }
    obj = parameters.remove("saturation"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      saturation = Integer.parseInt(value);
     }
    obj = parameters.remove("duration"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      duration = Integer.parseInt(value);
     }
    obj = parameters.remove("onlyOnChange"); //$NON-NLS-1$
    if (obj instanceof final String value)
     {
      onlyOnChange = Boolean.parseBoolean(value);
     }
   }


  /**
   * Get api proxy.
   *
   * @return API proxy
   */
  public AHASessionMini getApiProxy()
   {
    return apiProxy;
   }


  /**
   * Set api proxy.
   *
   * @param apiProxy API proxy
   */
  public void setApiProxy(final AHASessionMini apiProxy)
   {
    this.apiProxy = apiProxy;
   }


  /**
   * Get hostname.
   *
   * @return Hostname
   */
  public String getHostname()
   {
    return hostname;
   }


  /**
   * Set hostname.
   *
   * @param hostname Hhostname
   */
  public void setHostname(final String hostname)
   {
    this.hostname = hostname;
   }


  /**
   * Get port.
   *
   * @return Port
   */
  public int getPort()
   {
    return port;
   }


  /**
   * Set port.
   *
   * @param port Port
   */
  public void setPort(final int port)
   {
    this.port = port;
   }


  /**
   * Get username.
   *
   * @return Username
   */
  public String getUsername()
   {
    return username;
   }


  /**
   * Set username.
   *
   * @param username Username
   */
  public void setUsername(final String username)
   {
    this.username = username;
   }


  /**
   * Get password.
   *
   * @return Password
   */
  public String getPassword()
   {
    return password;
   }


  /**
   * Set password.
   *
   * @param password Password
   */
  public void setPassword(final String password)
   {
    this.password = password;
   }


  /**
   * Get actor identifier number.
   *
   * @return ain
   */
  public String getAin()
   {
    return ain;
   }


  /**
   * Set actor identifier number.
   *
   * @param ain ain
   */
  public void setAin(final String ain)
   {
    this.ain = ain;
   }


  /**
   * Get temperature.
   *
   * @return Temperature deci celsius temperature or kelvin 2700-6500.
   */
  public long getTemperature()
   {
    return temperature;
   }


  /**
   * Set temperature.
   *
   * @param temperature Temperature deci celsius temperature or kelvin 2700-6500.
   */
  public void setTemperature(final long temperature)
   {
    this.temperature = temperature;
   }


  /**
   * Get onoff.
   *
   * @return onoff 0 = off; 1 = on; 2 = toggle
   */
  public int getOnoff()
   {
    return onoff;
   }


  /**
   * Set onoff.
   *
   * @param onoff onoff: 0 = off; 1 = on; 2 = toggle
   */
  public void setOnoff(final int onoff)
   {
    this.onoff = onoff;
   }


  /**
   * Get level.
   *
   * @return Level 0-100
   */
  public int getLevel()
   {
    return level;
   }


  /**
   * Set level.
   *
   * @param level Level 0-100
   */
  public void setLevel(final int level)
   {
    this.level = level;
   }


  /**
   * Get hue.
   *
   * @return Hue 0-359
   */
  public int getHue()
   {
    return hue;
   }


  /**
   * Set hue.
   *
   * @param hue Hue 0-359
   */
  public void setHue(final int hue)
   {
    this.hue = hue;
   }


  /**
   * Get saturation.
   *
   * @return Saturation 0-255
   */
  public int getSaturation()
   {
    return saturation;
   }


  /**
   * Set saturation.
   *
   * @param saturation Saturation 0-255
   */
  public void setSaturation(final int saturation)
   {
    this.saturation = saturation;
   }


  /**
   * Get duration.
   *
   * @return Duration in milliseconds
   */
  public int getDuration()
   {
    return duration;
   }


  /**
   * Set duration.
   *
   * @param duration Duration in milliseconds
   */
  public void setDuration(final int duration)
   {
    this.duration = duration;
   }


  /**
   * Get end timestamp.
   *
   * @return Endtimestamp in seconds since 1970
   */
  public long getEndtimestamp()
   {
    return endtimestamp;
   }


  /**
   * Set end timestamp.
   *
   * @param endtimestamp Endtimestamp in seconds since 1970
   */
  public void setEndtimestamp(final long endtimestamp)
   {
    this.endtimestamp = endtimestamp;
   }


  /**
   * Get target.
   *
   * @return Target: open, close, stop
   */
  public String getTarget()
   {
    return target;
   }


  /**
   * Set target.
   *
   * @param target Target: open, close, stop
   */
  public void setTarget(final String target)
   {
    this.target = target;
   }


  /**
   * Get name.
   *
   * @return Device or group name
   */
  public String getName()
   {
    return name;
   }


  /**
   * Set name.
   *
   * @param name Device or group name
   */
  public void setName(final String name)
   {
    this.name = name;
   }


  /**
   * Get switch command.
   *
   * @return Switch command
   */
  public String getSwitchcmd()
   {
    return switchcmd;
   }


  /**
   * Set switch command.
   *
   * @param switchcmd Switch command
   */
  public void setSwitchcmd(final String switchcmd)
   {
    this.switchcmd = switchcmd;
   }


  /**
   * Is only on change (Consumer only).
   *
   * @return true: send exchange only on change.
   */
  public boolean isOnlyOnChange()
   {
    return onlyOnChange;
   }


  /**
   * Set only on change (Consumer only).
   *
   * @param onlyOnChange true: send exchange only on change; false otherwise
   */
  public void setOnlyOnChange(final boolean onlyOnChange)
   {
    this.onlyOnChange = onlyOnChange;
   }


  /**
   * Configure this class from uri.
   *
   * @param remaining Remaining URI string
   * @throws URISyntaxException URI syntax error
   */
  @SuppressFBWarnings({"CC_CYCLOMATIC_COMPLEXITY"})
  public void configure(final String remaining) throws URISyntaxException
   {
    String restUrl = remaining;
    final int pathStartPos = restUrl.indexOf('/');
    if (pathStartPos == -1)
     {
      throw new URISyntaxException("", "No switchcmd found!"); //$NON-NLS-1$ //$NON-NLS-2$
     }
    switchcmd = restUrl.substring(pathStartPos + 1);
    restUrl = restUrl.substring(0, pathStartPos);
    final int atPos = restUrl.indexOf('@');
    final String restUser = (atPos == -1) ? "" : restUrl.substring(0, atPos); //$NON-NLS-1$
    final String restHost = (atPos == -1) ? restUrl : restUrl.substring(atPos + 1);
    final int hostSepPos = restHost.indexOf(':');
    if (hostSepPos == -1)
     {
      hostname = restHost.isEmpty() ? "fritz.box" : restHost; //$NON-NLS-1$
      port = 443;
     }
    else
     {
      final var hostName = restHost.substring(0, hostSepPos);
      hostname = hostName.isEmpty() ? "fritz.box" : hostName; //$NON-NLS-1$
      port = Integer.parseInt(restHost.substring(hostSepPos + 1));
     }
    final int userSepPos = restUser.indexOf(':');
    if (userSepPos == -1)
     {
      username = restUser;
      password = ""; //$NON-NLS-1$
     }
    else
     {
      username = restUser.substring(0, userSepPos);
      password = restUser.substring(userSepPos + 1);
     }
   }


  /**
   * Check AIN.
   *
   * @return true: if correct AIN, false otherwise
   */
  private boolean checkAIN()
   {
    return (ain != null) && !ain.isEmpty() && !ain.isBlank();
    // (ain.length() != 12) && (ain.length() != 13) // 14/15
    // final String intAIN = ain.replaceAll("\\s", "");
    // intAIN.matches("^[0-9]{12}$") // -[0-9]
   }


  /**
   * Check on/off.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkOnOff()
   {
    return (onoff >= 0) && (onoff <= 2);
   }


  /**
   * Check level.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkLevel()
   {
    return (level >= 0) && (level <= 255);
   }


  /**
   * Check level percentage.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkLevelPercentage()
   {
    return (level >= 0) && (level <= 100);
   }


  /**
   * Check duration.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkDuration()
   {
    return (duration >= 0);
   }


  /**
   * Check hue and saturation.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkHueAndSaturation()
   {
    return (hue >= 0) && (hue <= 359) && (saturation >= 0) && (saturation <= 255);
   }


  /**
   * Check color temperature.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkColorTemperature()
   {
    return (temperature >= 2700) && (temperature <= 6500);
   }


  /**
   * Check end time stamp.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkEndTimeStamp()
   {
    return (endtimestamp >= 0) && (endtimestamp <= (Instant.now().getEpochSecond() + 86400));
   }


  /**
   * Check target.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkTarget()
   {
    return (target != null) && !target.isEmpty() && !target.isBlank() && ("open".equals(target) || "close".equals(target) || "stop".equals(target)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
   }


  /**
   * Check name.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkName()
   {
    return (name != null) && !name.isEmpty() && !name.isBlank();
   }


  /**
   * Check temperature.
   *
   * @return true: if correct, false otherwise
   */
  private boolean checkTemperature()
   {
    return (temperature == 0) || (temperature == 300) || ((temperature >= 80) && (temperature <= 280));
   }


  /**
   * Check if all parameters for the switchcmd are given.
   *
   * @return true: Check successful, false otherwise.
   */
  @SuppressFBWarnings({"CC_CYCLOMATIC_COMPLEXITY"})
  public boolean parameterCheck()
   {
    switch (switchcmd)
     {
      case "startulesubscription": //$NON-NLS-1$
      case "getswitchlist": //$NON-NLS-1$
      case "getdevicelistinfos": //$NON-NLS-1$
      case "gettemplatelistinfos": //$NON-NLS-1$
      case "getcolordefaults": //$NON-NLS-1$
      case "getsubscriptionstate": //$NON-NLS-1$
        return true;

      case "setswitchon": //$NON-NLS-1$
      case "setswitchoff": //$NON-NLS-1$
      case "setswitchtoggle": //$NON-NLS-1$
      case "applytemplate": //$NON-NLS-1$
      case "getswitchstate": //$NON-NLS-1$
      case "getswitchpresent": //$NON-NLS-1$
      case "getswitchpower": //$NON-NLS-1$
      case "getswitchenergy": //$NON-NLS-1$
      case "getswitchname": //$NON-NLS-1$
      case "gettemperature": //$NON-NLS-1$
      case "gethkrtsoll": //$NON-NLS-1$
      case "gethkrkomfort": //$NON-NLS-1$
      case "gethkrabsenk": //$NON-NLS-1$
      case "getbasicdevicestats": //$NON-NLS-1$
      case "getdeviceinfo": //$NON-NLS-1$
        return checkAIN();

      case "sethkrboost": //$NON-NLS-1$
      case "sethkrwindowopen": //$NON-NLS-1$
        return (checkAIN() && checkEndTimeStamp());

      case "sethkrtsoll": //$NON-NLS-1$
        return (checkAIN() && checkTemperature());

      case "setsimpleonoff": //$NON-NLS-1$
        return (checkAIN() && checkOnOff());

      case "setlevel": //$NON-NLS-1$
        return (checkAIN() && checkLevel());
      case "setlevelpercentage": //$NON-NLS-1$
        return (checkAIN() && checkLevelPercentage());

      case "setcolor": //$NON-NLS-1$
        return (checkAIN() && checkHueAndSaturation() && checkDuration());

      case "setcolortemperature": //$NON-NLS-1$
        return (checkAIN() && checkColorTemperature() && checkDuration());

      case "setblind": //$NON-NLS-1$
        return (checkAIN() && checkTarget());

      case "setname": //$NON-NLS-1$
        return (checkAIN() && checkName());

      default:
        throw new IllegalArgumentException("Unsupported switchcmd: " + switchcmd); //$NON-NLS-1$
     }
   }


  /**
   * Returns the string representation of this FBAHAConfiguration.
   *
   * The exact details of this representation are unspecified and subject to change, but the following may be regarded as typical:
   *
   * "FBAHAConfiguration[hostname=fritz.box, port=49443, username=, password=, ...]"
   *
   * @return String representation of this FBAHAConfiguration.
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
   {
    return new StringBuilder().append("FBAHAConfiguration[hostname=").append(hostname) //$NON-NLS-1$
      .append(", port=").append(port) //$NON-NLS-1$
      .append(", username=").append(username) //$NON-NLS-1$
      .append(", password=").append("XXX") //$NON-NLS-1$ //$NON-NLS-2$
      .append(", switchcmd=").append(switchcmd) //$NON-NLS-1$
      .append(", ain=").append(ain) //$NON-NLS-1$
      .append(", temperature=").append(temperature) //$NON-NLS-1$
      .append(", onoff=").append(onoff) //$NON-NLS-1$
      .append(", level=").append(level) //$NON-NLS-1$
      .append(", hue=").append(hue) //$NON-NLS-1$
      .append(", saturation=").append(saturation) //$NON-NLS-1$
      .append(", duration=").append(duration) //$NON-NLS-1$
      .append(", endtimestamp=").append(endtimestamp) //$NON-NLS-1$
      .append(", target=").append(target) //$NON-NLS-1$
      .append(", name=").append(name) //$NON-NLS-1$
      .append(", onlyonchange=").append(onlyOnChange) //$NON-NLS-1$
      .append(']').toString();
   }

 }
