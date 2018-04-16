package ru.fella.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import ru.fella.client.DailyInfoClient;
import ru.fella.client.impl.DailyInfoClientImpl;

/**
 * Created by efischenko on 12.04.2018.
 */
@Configuration
public class ClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.cbr.web");
        return marshaller;
    }

    @Bean()
    public DailyInfoClient quoteClient(@Value("${webService.uri}") String uri, Jaxb2Marshaller marshaller) {
        DailyInfoClientImpl client = new DailyInfoClientImpl();
        client.setDefaultUri(uri);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
