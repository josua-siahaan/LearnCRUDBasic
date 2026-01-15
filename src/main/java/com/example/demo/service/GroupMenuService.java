package com.example.demo.service;

import com.example.demo.core.Iservice;
import com.example.demo.dto.response.RespGroupMenuDTO;
import com.example.demo.dto.validation.ValGroupMenuDTO;
import com.example.demo.model.GroupMenu;
import com.example.demo.repo.GroupMenuRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Platform Code - USM
 * Modul Code -01
 * FV - FE
 */
@Service
@Transactional
public class GroupMenuService implements Iservice<GroupMenu> {


    @Autowired
    private GroupMenuRepo groupMenuRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<Object> insert(GroupMenu groupMenu) {
        if (groupMenu==null){
            return ResponseEntity.badRequest().body("USMFV001");
        }
        groupMenuRepo.save(groupMenu);
        return ResponseEntity.status(HttpStatus.CREATED).body("Succeses Created");
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
    public ResponseEntity<Object> findAll() {
        List<RespGroupMenuDTO> list = convertToRespGroupMenuDTO(groupMenuRepo.findAll());
        return ResponseEntity.ok(list);
    }

    public List<RespGroupMenuDTO> convertToRespGroupMenuDTO(List<GroupMenu> groupMenus){
        List<RespGroupMenuDTO> respGroupMenuDTOList = new ArrayList<>();
        for (GroupMenu groupMenu : groupMenus){
            RespGroupMenuDTO respGroupMenuDTO = new RespGroupMenuDTO();
            respGroupMenuDTO.setId(groupMenu.getId());
            respGroupMenuDTO.setNama(groupMenu.getNama());
            respGroupMenuDTO.setDeskripsi(groupMenu.getDeskripsi());
            respGroupMenuDTOList.add(respGroupMenuDTO);
        }
//        List<RespGroupMenuDTO> respGroupMenuDTOList = modelMapper.map(groupMenus, new TypeToken<List<RespGroupMenuDTO>>(){}.getType());
        return respGroupMenuDTOList;
    }

    public GroupMenu convertToEntity(ValGroupMenuDTO valGroupMenuDTO){
        GroupMenu groupMenu = new GroupMenu();
        groupMenu.setNama(valGroupMenuDTO.getNama());
        groupMenu.setDeskripsi(valGroupMenuDTO.getDeskripsi());
//        GroupMenu groupMenu = modelMapper.map(valGroupMenuDTO, GroupMenu.class);
        return groupMenu;
    }
}
