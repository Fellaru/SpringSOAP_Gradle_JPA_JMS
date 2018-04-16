package ru.fella.client;

import org.springframework.beans.factory.InitializingBean;
import ru.cbr.web.GetCursOnDateXMLResponse;

import java.util.Date;

/**
 * Created by efischenko on 13.04.2018.
 */
public interface DailyInfoClient extends InitializingBean {
    GetCursOnDateXMLResponse getCursOnDate(Date date);
}
