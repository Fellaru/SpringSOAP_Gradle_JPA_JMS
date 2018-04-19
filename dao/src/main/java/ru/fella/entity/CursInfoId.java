package ru.fella.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by efischenko on 18.04.2018.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CursInfoId implements Serializable {
    private BigDecimal curs;
    @OneToOne
    @JoinColumn(name="valute_id")
    private ValuteInfo valuteInfo;

}