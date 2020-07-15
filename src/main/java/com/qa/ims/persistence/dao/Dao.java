package com.qa.ims.persistence.dao;

import java.util.List;

public interface Dao<T> {

    List<T> readAll();
     
    T create(T t);
    
    void calc(long id);
     
    T update(T t);
     
    void delete(long id);
}
	