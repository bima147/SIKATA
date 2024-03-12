package coid.bcafinance.bpsringbootfinal.configuration;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 12/03/2024 15:31
@Last Modified 12/03/2024 15:31
Version 1.0
*/

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 CLASS YANG HANYA MEMPROSES DATA REQUEST APA ADA NYA TANPA VALIDASI APAPUN YANG MURNI DARI EXTERNAL (API ATAUPUN FE)
 GUNANYA PADA SAAT CLASS YANG MyHttpServletRequestWrapper Mendapati serangan hacker
 MAKA DATA NYA MASIH BISA DI CAPTURE BERDASARKAN DARI CLASS INI
 */
public class RawHttpServletRequestWrapper extends HttpServletRequestWrapper { //diubah
    private final String body;
    private String [] strExceptionArr = {"MyHttpServletRequestWrapper",""};
    public RawHttpServletRequestWrapper(HttpServletRequest request) {
        /** agar request dikembalikan ke semula*/
        super(request);
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
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                }
            }
        }
        body = stringBuilder.toString();
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
        return body;
    }
}