package ru.fella.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

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
    public DailyInfoClient quoteClient(Jaxb2Marshaller marshaller) {
        DailyInfoClient client = new DailyInfoClient();
        client.setDefaultUri("http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
