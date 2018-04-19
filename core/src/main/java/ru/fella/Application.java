package ru.fella;

import generated.ValuteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import ru.fella.client.CursOnDateSevice;
import ru.fella.dao.ValuteInfoDao;
import ru.fella.entity.ValuteInfo;

@SpringBootApplication
public class Application implements CommandLineRunner{
    @Autowired
    CursOnDateSevice cursOnDateSevice;

    @Autowired
    ValuteInfoDao valuteInfoDao;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        ValuteData vd = cursOnDateSevice.getValuteCurs();
        System.out.println(vd.getValuteCursOnDate().get(0).getVname());

        ValuteInfo valuteInfo = new ValuteInfo();
        valuteInfo.setValuteName("Австралийский доллар");
        valuteInfoDao.save(valuteInfo);
    }
}
