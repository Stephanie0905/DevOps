package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.common.IByNameApi;
import com.example.demo.entities.User;



public interface IUserJpaDao extends JpaRepository<User, Long>, IByNameApi<User> {
    //
}
