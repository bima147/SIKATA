package coid.bcafinance.bpsringbootfinal.controller;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 02/03/2024 19:40
@Last Modified 02/03/2024 19:40
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.configuration.OtherConfig;
import coid.bcafinance.bpsringbootfinal.dto.user.authentication.UsersLoginDTO;
import coid.bcafinance.bpsringbootfinal.dto.user.authentication.UsersRegistrationDTO;
import coid.bcafinance.bpsringbootfinal.dto.user.authentication.UsersResetPasswordDTO;
import coid.bcafinance.bpsringbootfinal.handler.RequestCapture;
import coid.bcafinance.bpsringbootfinal.handler.ResponseHandler;
import coid.bcafinance.bpsringbootfinal.model.User;
import coid.bcafinance.bpsringbootfinal.service.UsersService;
import coid.bcafinance.bpsringbootfinal.service.authentication.RegisterService;
import coid.bcafinance.bpsringbootfinal.util.ConstantMessage;
import coid.bcafinance.bpsringbootfinal.util.ExecuteSMTP;
import coid.bcafinance.bpsringbootfinal.util.LoggingFile;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class AuthenticationController {
    private String[] strExceptionArr = new String[2];

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UsersService usersService;

    @PostMapping("/v1/auth/login")
    public ResponseEntity<Object> login(@Valid @RequestBody UsersLoginDTO usersLoginDTO,
                        HttpServletRequest request) {
        if(usersLoginDTO == null) {
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_DATA_INVALID, HttpStatus.BAD_REQUEST,null,null,null);
        }
        User user = modelMapper.map(usersLoginDTO,new TypeToken<User>(){}.getType());
        return usersService.doLogin(user,request);
    }

//    @GetMapping("/tembak")
//    public ResponseEntity<Object> tembak(HttpServletRequest request) {
//        String[] strVerify = new String[3];
//        strVerify[0] = "Verifikasi Email";
//        strVerify[1] = "Fauzan";
//        strVerify[2] = String.valueOf(1);
//        for (int i = 0; i < 1000; i++) {
//            Thread first = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        new ExecuteSMTP().sendSMTPToken("0xf4ray@gmail.com","TOKEN Verifikasi Email",strVerify,
//                                "ver_regis.html");// \ver_regis
//                        System.out.println("Email Terkirim");
//                    } catch (Exception e) {
//
//                        strExceptionArr[1] = "Tembak"+ RequestCapture.allRequest(request);
//                        LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
////                        LogTable.inputLogRequest(strExceptionArr,e, OtherConfig.getFlagLogTable());
//                    }
//                }
//            });
//            first.start();
//        }
//        return null;
//    }

    @PostMapping("/v1/auth/register")
    public ResponseEntity<Object>
    RegisterService(@Valid @RequestBody UsersRegistrationDTO usersRegistrationDTO,
                    HttpServletRequest request) throws Exception {
        if (usersRegistrationDTO == null) {
            return new ResponseHandler().generateResponse(ConstantMessage.ERROR_DATA_INVALID, HttpStatus.BAD_REQUEST,null,null,null);
        }

        User user = modelMapper.map(usersRegistrationDTO, new TypeToken<User>() {}.getType());
        return usersService.checkRegis(user,request);
    }

    @PostMapping("/v1/auth/forget-password")
    public String forgetPassword(@Valid @RequestBody UsersResetPasswordDTO usersResetPasswordDTO) {
        if(usersResetPasswordDTO == null) {
            return "Reset password tidak berhasil!";
        }
        return "Reset password berhasil";
    }
}