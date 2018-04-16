package ru.fella.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
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
    public DailyInfoClientImpl quoteClient(Jaxb2Marshaller marshaller) {
        DailyInfoClientImpl client = new DailyInfoClientImpl();
        client.setDefaultUri("http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
