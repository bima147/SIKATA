package coid.bcafinance.bpsringbootfinal.handler;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 12/03/2024 15:28
@Last Modified 12/03/2024 15:28
Version 1.0
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class XSSParamException extends RuntimeException{ //tambah
    public XSSParamException() {
    }

    public XSSParamException(String message) {
        super(message);
    }
}
