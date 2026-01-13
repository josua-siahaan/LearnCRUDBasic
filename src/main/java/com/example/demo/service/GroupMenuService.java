package com.example.demo.service;

import com.example.demo.core.Iservice;
import com.example.demo.model.GroupMenu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Platform Code - USM
 * Modul Code -01
 * FV - FE
 */
@Service
@Transactional
public class GroupMenuService implements Iservice<GroupMenu> {


    @Override
    public void insert(GroupMenu groupMenu) {

    }

    @Override
    public void update(Long id, GroupMenu groupMenu) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GroupMenu findBy(Long id) {
        return null;
    }

    @Override
    public List<GroupMenu> findAll() {
        return List.of();
    }
}
