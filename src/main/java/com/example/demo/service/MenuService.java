package com.example.demo.service;

import com.example.demo.core.IReport;
import com.example.demo.core.Iservice;
import com.example.demo.model.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuService implements Iservice<Menu>, IReport<Menu> {
    @Override
    public void insert(Menu menu) {

    }

    @Override
    public void update(Long id, Menu menu) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Menu findBy(Long id) {
        return null;
    }

    @Override
    public List<Menu> findAll() {
        return List.of();
    }

    @Override
    public void uploadExcel(Menu menu) {

    }

    @Override
    public void dowloadExcel(Menu menu) {

    }

    @Override
    public void uploadImage(Menu menu) {

    }

    @Override
    public void dowloadImage(Menu menu) {

    }

    @Override
    public void dowloadPdf(Menu menu) {

    }
}
