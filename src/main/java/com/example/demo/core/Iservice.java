package com.example.demo.core;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

/** Ini kontrak untuk handdling CRUD */

public interface Iservice<T> {

    ResponseEntity<Object> insert(T t, HttpServletRequest request); //001 - 010
    void update(Long id, T t);//011 - 020
    void delete(Long id);//021 - 030
    T findBy(Long id);//031 - 040
    ResponseEntity<Object> findAll();//041 - 050
}
