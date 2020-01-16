package com.example.demo.common;

import java.io.Serializable;



public interface IEntity extends Serializable {

	Long getId();

    void setId(final Long id);
    
    String getName();
    void setName(final String name);
    //

}
