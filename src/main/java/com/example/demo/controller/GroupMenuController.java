package com.example.demo.controller;

import com.example.demo.dto.validation.ValGroupMenuDTO;
import com.example.demo.model.GroupMenu;
import com.example.demo.service.GroupMenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group-menu")
public class GroupMenuController {

    @Autowired
    private GroupMenuService groupMenuService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return groupMenuService.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValGroupMenuDTO valGroupMenuDTO){
        return groupMenuService.insert(groupMenuService.convertToEntity(valGroupMenuDTO));
    }
}
