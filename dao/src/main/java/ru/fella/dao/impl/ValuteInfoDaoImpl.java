package ru.fella.dao.impl;

import ru.fella.dao.ValuteInfoDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fella.entity.ValuteInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by efischenko on 19.04.2018.
 */
@Repository
@Transactional
public class ValuteInfoDaoImpl implements ValuteInfoDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(ValuteInfo valuteInfo) {
        em.persist(valuteInfo);
    }
}
