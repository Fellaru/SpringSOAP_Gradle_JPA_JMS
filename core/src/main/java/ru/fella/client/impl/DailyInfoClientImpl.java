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

    private final String soapAction = "http://web.cbr.ru/GetCursOnDateXML";



    @Override
    public GetCursOnDateXMLResponse getCursOnDate(Date date) {
        GetCursOnDateXML request = getCursOnDateXMLwith(date);
        log.info("Requesting exchange rate for " + request.getOnDate());
        GetCursOnDateXMLResponse response = marshalSendAndReceiveUnmarshal(request, soapAction);
        return response;
    }

    private GetCursOnDateXML getCursOnDateXMLwith(Date date) {
        GetCursOnDateXML cursOnDateXML = new GetCursOnDateXML();
        cursOnDateXML.setOnDate(convertDatetoXMLGrigorianCalendar(date));
        return cursOnDateXML;
    }

    @SneakyThrows(DatatypeConfigurationException.class)
    private XMLGregorianCalendar convertDatetoXMLGrigorianCalendar(Date date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar currentDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return currentDate;
    }

    private GetCursOnDateXMLResponse marshalSendAndReceiveUnmarshal(GetCursOnDateXML requestPayload, String soapAction) {
        WebServiceTemplate webServiceTemplate = getWebServiceTemplate();

        Object response = webServiceTemplate.marshalSendAndReceive(
                requestPayload,
                new SoapActionCallback(soapAction)
        );

        return (GetCursOnDateXMLResponse) response;
    }


}
