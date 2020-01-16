package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.AbstractService;
import com.example.demo.dao.IUserJpaDao;
import com.example.demo.entities.User;


@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements IUserService {

    @Autowired
    IUserJpaDao dao;

    public UserServiceImpl() {
        super();
    }

    // API

    // find
    @Override
	public User findByName(String name) {
		return getDao().findByName(name);
	}

    // Spring

    @Override
    protected final IUserJpaDao getDao() {
        return dao;
    }

	


}
