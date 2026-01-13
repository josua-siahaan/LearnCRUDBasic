package com.example.demo.core;

import java.util.List;

/** Ini kontrak untuk handdling CRUD */

public interface Iservice<T> {

    void insert(T t); //001 - 010
    void update(Long id, T t);//011 - 020
    void delete(Long id);//021 - 030
    T findBy(Long id);//031 - 040
    List<T> findAll();//041 - 050
}
