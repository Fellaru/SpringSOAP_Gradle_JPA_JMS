package ru.fella.client.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import ru.cbr.web.GetCursOnDateXML;
import ru.cbr.web.GetCursOnDateXMLResponse;
import ru.fella.client.DailyInfoClient;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;


@Slf4j
public class DailyInfoClientImpl extends WebServiceGatewaySupport implements DailyInfoClient {

    private String URI = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";

    @Override
    public GetCursOnDateXMLResponse getCursOnDate(Date date) {
        GetCursOnDateXML request = getCursOnDateXMLwith(date);
        System.out.println("Requesting exchange rate for " + request.getOnDate());
        GetCursOnDateXMLResponse response = marshalSendAndReceiveUnmarshal(URI, request);
        return response;
    }

    @SneakyThrows(DatatypeConfigurationException.class)
    private XMLGregorianCalendar convertDatetoXMLGrigorianCalendar(Date date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar currentDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return currentDate;
    }

    private GetCursOnDateXML getCursOnDateXMLwith(Date date) {
        GetCursOnDateXML cursOnDateXML = new GetCursOnDateXML();
        cursOnDateXML.setOnDate(convertDatetoXMLGrigorianCalendar(date));
        return cursOnDateXML;
    }

    private GetCursOnDateXMLResponse marshalSendAndReceiveUnmarshal(String uri, GetCursOnDateXML requestPayload) {
        WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

        Object response = webServiceTemplate.marshalSendAndReceive(
                URI,
                requestPayload,
                new SoapActionCallback("http://web.cbr.ru/GetCursOnDateXML")
        );

        return (GetCursOnDateXMLResponse) response;
    }


}
