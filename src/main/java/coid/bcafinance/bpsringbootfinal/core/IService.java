package coid.bcafinance.bpsringbootfinal.core;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 9:40
@Last Modified 05/03/2024 9:40
Version 1.0
*/

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IService<T> {

    public ResponseEntity<Object> save(T t, HttpServletRequest request);//001-010
    public ResponseEntity<Object> saveBatch(List<T> lt, HttpServletRequest request);//011-020
    public ResponseEntity<Object> edit(Long id,T t,HttpServletRequest request);//021-030
    public ResponseEntity<Object> delete(Long id,HttpServletRequest request);//031-040//del
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request);//KODE DISAMAKAN
    public ResponseEntity<Object> find(Pageable pageable, String columFirst, String valueFirst, HttpServletRequest request);//KODE DISAMAKAN
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request);//061-070
}