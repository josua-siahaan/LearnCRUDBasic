package com.example.demo.controller;

import com.example.demo.dto.validation.ValGroupMenuDTO;
import com.example.demo.model.GroupMenu;
import com.example.demo.service.GroupMenuService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group-menu")
public class GroupMenuController {

    @Autowired
    private GroupMenuService groupMenuService;

    @GetMapping
    public ResponseEntity<Object> findAll(Pageable pageable, HttpServletRequest request){
        return groupMenuService.findAll(pageable, request);
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ValGroupMenuDTO valGroupMenuDTO, HttpServletRequest request){
        return groupMenuService.save(groupMenuService.convertToEntity(valGroupMenuDTO), request);
    }
}
