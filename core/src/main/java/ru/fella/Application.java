package ru.fella;

import generated.ValuteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.fella.client.impl.CursOnDateSevice;

@SpringBootApplication
public class Application implements CommandLineRunner{
    @Autowired
    CursOnDateSevice cursOnDateSevice;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ValuteData vd = cursOnDateSevice.getValuteCurs();
        System.out.println(vd.getValuteCursOnDate().get(0).getVname());
    }
}
