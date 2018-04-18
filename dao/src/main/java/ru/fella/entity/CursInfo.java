package ru.fella.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * Created by efischenko on 16.04.2018.
 */

@Entity
@Data
public class CursInfo {
    private Timestamp date;
    @EmbeddedId
    private CursInfoId id;
}
