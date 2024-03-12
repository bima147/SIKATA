package coid.bcafinance.bpsringbootfinal.service;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 10:36
@Last Modified 05/03/2024 10:36
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.core.IService;
import coid.bcafinance.bpsringbootfinal.model.Access;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

/**
 *  Company Code - Not Necessery
 *  Modul Code 03
 *  Type of Error -> Validation = FV , Engine Error = FE
 *  ex : FE01001 (Error di Modul GroupMenu Functional Save)
 */
@Service
@Transactional
public class AksesService implements IService<Access> {

    @Override
    public ResponseEntity<Object> save(Access access, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> saveBatch(List<Access> lt, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> edit(Long id, Access access, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> find(Pageable pageable, String columFirst, String valueFirst, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }
}
