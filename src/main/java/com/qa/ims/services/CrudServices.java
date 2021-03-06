package com.qa.ims.services;

import java.util.List;

public interface CrudServices<T> {

    public List<T> readAll();
     
    T create(T t);
     
    T update(T t);
 
    void delete(Long id);

	//void calc(Long id, T t);
    T calc(Long id);

}
