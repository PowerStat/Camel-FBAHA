/*
 * Copyright (C) 2022-2023 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * XML helper tests.
 */
public class XMLHelperTest
 {
  /**
   * Test convert document to string.
   *
   * @throws TransformerFactoryConfigurationError Transformer factory configuration error
   * @throws TransformerException Transformer exception
   * @throws IOException  IO exception
   * @throws SAXException SAX exception
   * @throws ParserConfigurationException Parser configuration error
   */
  @Test
  public void convertDocumentToString1() throws TransformerFactoryConfigurationError, TransformerException, SAXException, IOException, ParserConfigurationException
   {
    final DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    final InputSource source = new InputSource();
    source.setCharacterStream(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><state code=\"0\"><latestain/></state>")); //$NON-NLS-1$
    final Document doc = docBuilder.parse(source);
    final String result = XMLHelper.convertDocumentToString(doc);
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n<state code=\"0\">\n    <latestain/>\n</state>\n", result.replace("\r\n", "\n"), "not equal"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
   }

 }
