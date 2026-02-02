package com.example.demo.service;

import com.example.demo.core.Iservice;
import com.example.demo.dto.rel.RelGroupMenuDTO;
import com.example.demo.dto.rel.RelMenuDTO;
import com.example.demo.dto.validation.ValAksesDTO;
import com.example.demo.model.Akses;
import com.example.demo.model.Menu;
import com.example.demo.repo.AksesRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AksesService implements Iservice<Akses> {

    @Autowired
    private AksesRepo aksesRepo;


    @Override
    public ResponseEntity<Object> insert(Akses akses, HttpServletRequest request) {
        aksesRepo.save(akses);
        return ResponseEntity.status(HttpStatus.CREATED).body("Berhasil di Simpan");
    }

    @Override
    public void update(Long id, Akses akses) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Akses findBy(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAll() {
        return null;
    }

    public Akses convertToEntity(ValAksesDTO valAksesDTO){
        Akses akses = new Akses();
        akses.setNama(valAksesDTO.getNama());
        akses.setDeskripsi(valAksesDTO.getDeskripsi());
        List<Menu> ltMenu = new ArrayList<>();
        for (RelMenuDTO relMenuDTO : valAksesDTO.getLtMenu()){
            Menu menu = new Menu();
            menu.setId(relMenuDTO.getId());
            ltMenu.add(menu);
        }
        akses.setLtMenu(ltMenu);
        return akses;

    }
}
