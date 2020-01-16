package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.AbstractService;
import com.example.demo.dao.IRoleJpaDao;
import com.example.demo.entities.Role;



@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {

    @Autowired
    IRoleJpaDao dao;

    public RoleServiceImpl() {
        super();
    }

    // API
    @Override
	public Role findByName(String name) {
		return getDao().findByName(name);
	}

    // create

    @Override
    public Role create(final Role entity) {
        /*
         * final long id = IdUtil.randomPositiveLong(); entity.setId( id );
         */

        /*
         * final List< Privilege > associationsTemp = Lists.newArrayList( entity.getPrivileges() ); entity.getPrivileges().clear(); for( final Privilege privilege : associationsTemp ){ entity.getPrivileges().add(
         * associationDao.findByName( privilege.getName() ) ); }
         */

        return super.create(entity);
    }

    // Spring

    @Override
    protected final IRoleJpaDao getDao() {
        return dao;
    }

	


}
