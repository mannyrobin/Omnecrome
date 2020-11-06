/*
 * Decompiled with CFR 0.143.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 */
package com.sap.document.sap.rfc.functions.utils;

import org.apache.commons.io.IOUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EasuFdhJaxbUtils {
    protected static String PACKAGE = "com.sap.document.sap.rfc.functions";

    public static Object getObject(InputStream is) throws JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(PACKAGE);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Object o = unmarshaller.unmarshal(is);
            return o;
        }
        catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }

    public static Object getSoapObject(InputStream is) throws IOException, SOAPException, JAXBException {
        try {
            JAXBContext jc = JAXBContext.newInstance(PACKAGE);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(IOUtils.toByteArray((InputStream)is)));
            Object o = unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());
            return o;
        }
        catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }

    public static void getXml(Object o, OutputStream os) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(PACKAGE);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", true);
        marshaller.marshal(o, os);
    }
}

