/*
 * Copyright (C) 2022 Dipl.-Inform. Kai Hofmann. All rights reserved!
 */
package de.powerstat.camel.component.fbaha;


import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


/**
 * XML helper.
 */
@SuppressWarnings("java:S1160")
public final class XMLHelper
 {
  /**
   * Disabled default constructor.
   */
  private XMLHelper()
   {
    super();
   }


  /**
   * Convert xml document to string.
   *
   * @param doc XML document
   * @return XMl as string formatted
   * @throws TransformerFactoryConfigurationError Transformer factory configuration error
   * @throws TransformerConfigurationException Transformer configuration exception
   * @throws TransformerException Transformer exception
   */
  public static String convertDocumentToString(final Document doc) throws TransformerFactoryConfigurationError, TransformerException
   {
    final TransformerFactory factory = TransformerFactory.newInstance();
    factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
    factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, ""); //$NON-NLS-1$
    factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, ""); //$NON-NLS-1$
    final Transformer transformer = factory.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no"); //$NON-NLS-1$
    transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
    transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //$NON-NLS-1$
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //$NON-NLS-1$
    final StringWriter writer = new StringWriter();
    transformer.transform(new DOMSource(doc), new StreamResult(writer));
    return writer.toString();
   }

 }
