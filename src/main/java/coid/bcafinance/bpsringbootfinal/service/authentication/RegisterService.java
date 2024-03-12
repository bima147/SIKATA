package coid.bcafinance.bpsringbootfinal.service.authentication;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 02/03/2024 19:40
@Last Modified 02/03/2024 19:40
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.dto.user.authentication.UsersRegistrationDTO;
import coid.bcafinance.bpsringbootfinal.model.User;
import coid.bcafinance.bpsringbootfinal.core.IService;
import coid.bcafinance.bpsringbootfinal.repo.UsersRepo;
import coid.bcafinance.bpsringbootfinal.util.TransformToDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *  Company Code - Not Necessery
 *  Modul Code 01
 *  Type of error -> Validation = FV, Error = FE
 *  ex: FE01001 (Error di Groupmenu Functional Save)
 */
@Service
@Transactional
public class RegisterService implements IService<User> {
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ModelMapper modelMapper;

    Map<String,Object> mapColum = new HashMap<>();

    TransformToDTO transformToDTO = new TransformToDTO();

    Map<String,Object> mapResult = new HashMap<>();

    private String [] strExceptionArr = new String[2];

    public RegisterService() {
        strExceptionArr[0] = "RegisterService";
        mapColum.put("id","idGroupMenu");
        mapColum.put("nama","namaGroupMenu");
    }

//    public Optional<User> getUserByEmail(UsersRegistrationDTO usersRegistrationDTO, HttpServletRequest request) {
//        Optional<User> cek = usersRepo.findFirstByEmailContainingIgnoreCase(usersRegistrationDTO.getEmail());
//        return cek;
//    }

    @Override
    public ResponseEntity<Object> save(User user, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> saveBatch(List<User> lt, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> edit(Long id, User user, HttpServletRequest request) {
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
