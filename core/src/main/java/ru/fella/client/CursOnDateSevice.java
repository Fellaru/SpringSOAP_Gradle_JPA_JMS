package ru.fella.client;

import generated.ValuteData;
import lombok.SneakyThrows;

/**
 * Created by efischenko on 16.04.2018.
 */
public interface CursOnDateSevice {
    @SneakyThrows
    ValuteData getValuteCurs();
}
