package ru.fella.dao.impl;

import ru.fella.dao.CursInfoDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fella.entity.CursInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by efischenko on 19.04.2018.
 */
@Repository
@Transactional
public class CursInfoDaoImpl implements CursInfoDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(CursInfo cursInfo) {
        em.persist(cursInfo);
    }
}
