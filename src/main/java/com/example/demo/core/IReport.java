package com.example.demo.core;

public interface IReport<T> {

    public void uploadExcel(T t);
    public void dowloadExcel(T t);
    public void uploadImage(T t);
    public void dowloadImage(T t);
    public void dowloadPdf(T t);
}
