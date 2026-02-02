package com.example.demo.dto.validation;

import com.example.demo.dto.rel.RelGroupMenuDTO;

public class ValMenuDTO {

    private String nama;

    private String path;

    private RelGroupMenuDTO groupMenu;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RelGroupMenuDTO getGroupMenu() {
        return groupMenu;
    }

    public void setGroupMenu(RelGroupMenuDTO groupMenu) {
        this.groupMenu = groupMenu;
    }
}
