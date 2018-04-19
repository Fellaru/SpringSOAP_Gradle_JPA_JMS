package ru.fella.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by efischenko on 18.04.2018.
 */
@Entity
@Data
@NoArgsConstructor
public class ValuteInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int valuteId;
    private String valuteName;
}
