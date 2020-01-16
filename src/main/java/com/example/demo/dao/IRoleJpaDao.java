package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.common.IByNameApi;
import com.example.demo.entities.Role;



public interface IRoleJpaDao extends JpaRepository<Role, Long>, IByNameApi<Role> {
    //
}
