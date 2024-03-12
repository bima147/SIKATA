package coid.bcafinance.bpsringbootfinal.configuration;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 02/03/2024 19:40
@Last Modified 02/03/2024 19:40
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.handler.XSSAttackExcception;
import coid.bcafinance.bpsringbootfinal.handler.XSSParamException;
import coid.bcafinance.bpsringbootfinal.handler.XSSResponse;
import coid.bcafinance.bpsringbootfinal.util.LoggingFile;
import coid.bcafinance.bpsringbootfinal.util.XSSValidationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.List;

/**
 Data stream servlet saat sudah dipanggil akan hilang
 prose override pada class ini untuk membuat object penampung data nya
 dengan tujuan agar data nya dapat digunakan di class lain.
 UNTUK PROJECT INI DITAMBAHKAN XSS VALIDATION
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper { //diubah
    private final String body;
    private List<String> listKataTerlarang;
    private String [] strExceptionArr = {"MyHttpServletRequestWrapper",""};
    public MyHttpServletRequestWrapper(HttpServletRequest request,List<String> listKataTerlarang) {
        /** agar request dikembalikan ke semula*/
        super(request);
        this.listKataTerlarang = listKataTerlarang;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();

            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                char[] charBuffer = new char[128];
                int bytesRead = -1;

                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
//            System.out.println("Error reading the request body...");
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
//                    System.out.println("Error closing bufferedReader...");
                }
            }
        }
        body = stringBuilder.toString();
    }

    private boolean filteringz(String input) {
//        System.out.println("param:" + input);
        XSSResponse errorResponse = new XSSResponse();
        if (!XSSValidationUtils.isValidURL(input, listKataTerlarang)) {

            errorResponse.setStatus(HttpStatus.FORBIDDEN.value());
            errorResponse.setMessage("Anda diketahui Berniat Jahat !!");
            try {
                String response = XSSValidationUtils.convertObjectToJson(errorResponse);
                strExceptionArr[1] = "filteringz(String input) -> Value : Request Param ("+input+")";
                LoggingFile.exceptionStringz(strExceptionArr, new XSSParamException("Serangan Hacker"), OtherConfig.getFlagLoging());
                throw new XSSParamException(response);
            }
            catch (JsonProcessingException e) {

                return false;
            }
        }
        return true;
    }

    /** XSS :  VALIDASI QUERY PARAM */
    @Override
    public String getParameter(String paramName) {
        String value = super.getParameter(paramName);
        filteringz(value);
        return value;
    }

    /** XSS :  VALIDASI QUERY PARAM */
    @Override
    public String[] getParameterValues(String paramName) {
        String values[] = super.getParameterValues(paramName);
        if (null != values) {
            for (int index = 0; index < values.length; index++) {
                filteringz(values[index]);
            }
        }
        return values;
    }

    @Override
    public ServletInputStream getInputStream () {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());

        ServletInputStream inputStream = new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            public int read () throws IOException {
                return byteArrayInputStream.read();
            }
        };

        return inputStream;
    }

    @Override
    public BufferedReader getReader(){
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    public String getBody() {
        return this.body;
    }
}