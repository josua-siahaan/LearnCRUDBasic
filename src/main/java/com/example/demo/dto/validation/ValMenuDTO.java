package com.example.demo.dto.validation;

import com.example.demo.dto.report.RepGroupMenuDTO;
import com.example.demo.model.GroupMenu;

public class ValMenuDTO {

    private String nama;

    private String path;

    private RepGroupMenuDTO groupMenu;


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

    public RepGroupMenuDTO getGroupMenu() {
        return groupMenu;
    }

    public void setGroupMenu(RepGroupMenuDTO groupMenu) {
        this.groupMenu = groupMenu;
    }
}
