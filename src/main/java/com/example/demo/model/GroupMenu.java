package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MstGroupMenus")
public class GroupMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Nama", nullable = false, length = 50, unique = true)
    private String nama;

    @Column(name = "Deskripsi", nullable = false, length = 50, unique = true)
    private String deskripsi;
}
