package ru.fella.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by efischenko on 18.04.2018.
 */
@Entity
@Data
public class ValuteInfo {
    private int valuteId;
    private String valuteName;
}
