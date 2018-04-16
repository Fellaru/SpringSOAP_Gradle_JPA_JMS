package ru.fella.client.impl;

import generated.ValuteData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.cbr.web.GetCursOnDateXMLResponse;
import ru.fella.client.DailyInfoClient;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.Date;

/**
 * Created by efischenko on 13.04.2018.
 */
@Service
public class CursOnDateSevice {

    @Autowired
    DailyInfoClient client;

    @SneakyThrows
    public ValuteData getValuteCurs() {
        GetCursOnDateXMLResponse cursOnDateXMLResponse = client.getCursOnDate(new Date());
        Element element = (Element) (cursOnDateXMLResponse.getGetCursOnDateXMLResult().getContent().get(0));
        return (ValuteData)getUnmarshaller().unmarshal(element.getOwnerDocument());

    }


    public ValuteData getValuteData() throws JAXBException, SAXException {
        File file = new File("C://LEARN/EducationProject/core/src/main/resources/ValuteDate.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(ValuteData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("C://LEARN/EducationProject/daily-info-ws/src/main/resources/xsd/ValuteData.xsd"));

        jaxbUnmarshaller.setSchema(schema);
        ValuteData valuteData = (ValuteData) jaxbUnmarshaller.unmarshal(file);
        return valuteData;
    }

    @SneakyThrows
    public Unmarshaller getUnmarshaller() {
        JAXBContext jaxbContext = JAXBContext.newInstance(ValuteData.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("C://LEARN/EducationProject/daily-info-ws/src/main/resources/xsd/ValuteData.xsd"));

        jaxbUnmarshaller.setSchema(schema);
        return jaxbUnmarshaller;
    }

}
