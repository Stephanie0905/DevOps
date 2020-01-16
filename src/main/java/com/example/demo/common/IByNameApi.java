package com.example.demo.common;

public interface IByNameApi<T extends IEntity> {

    T findByName(final String name);

}
