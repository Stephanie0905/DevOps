package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.common.IByNameApi;
import com.example.demo.entities.Privilege;


@Repository
public interface IPrivilegeJpaDao extends JpaRepository<Privilege, Long>,IByNameApi<Privilege> {
    //
}
