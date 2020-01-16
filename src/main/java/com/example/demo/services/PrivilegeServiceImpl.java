package com.example.demo.services;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.AbstractService;
import com.example.demo.dao.IPrivilegeJpaDao;
import com.example.demo.entities.Privilege;



@Service
@Transactional
public class PrivilegeServiceImpl extends AbstractService<Privilege> implements IPrivilegeService {

    @Autowired
    IPrivilegeJpaDao dao;

    public PrivilegeServiceImpl() {
        super();
    }


    // Spring

    @Override
    protected final IPrivilegeJpaDao getDao() {
        return dao;
    }


 // find

    @Override
    public Privilege findByName(final String name) {
        return getDao().findByName(name);
    }

	

}
