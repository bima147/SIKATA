package coid.bcafinance.bpsringbootfinal.core.security;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 07/03/2024 11:02
@Last Modified 07/03/2024 11:02
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.configuration.MyHttpServletRequestWrapper;
import coid.bcafinance.bpsringbootfinal.configuration.OtherConfig;
import coid.bcafinance.bpsringbootfinal.configuration.RawHttpServletRequestWrapper;
import coid.bcafinance.bpsringbootfinal.core.Crypto;
import coid.bcafinance.bpsringbootfinal.handler.RequestCapture;
import coid.bcafinance.bpsringbootfinal.handler.XSSAttackExcception;
import coid.bcafinance.bpsringbootfinal.handler.XSSParamException;
import coid.bcafinance.bpsringbootfinal.handler.XSSResponse;
import coid.bcafinance.bpsringbootfinal.service.UsersService;
import coid.bcafinance.bpsringbootfinal.util.LoggingFile;
import coid.bcafinance.bpsringbootfinal.util.XSSValidationUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private UsersService usersService;

    @Autowired
    private ObjectMapper objectMapper;
    private String [] strExceptionArr = new String[2];
    public JwtFilter() {
        strExceptionArr[0] = "JwtFilter";
    }
    private XSSResponse xssResponse = new XSSResponse();

    @Override
    public void doFilterInternal(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");//ambil header Authorization
        authorization = authorization == null ? "": authorization;
        String token = null;
        String userName = null;
        MyHttpServletRequestWrapper requestWrapper = null;
        RawHttpServletRequestWrapper rawRequest = new RawHttpServletRequestWrapper(request);
        try{
            /**
                validasi khusus memilah request dengan content type application/json
             */
            String strContentType = request.getContentType()==null?"":request.getContentType();
            if(!strContentType.startsWith("multipart/form-data") || "".equals(strContentType)){
                requestWrapper = new MyHttpServletRequestWrapper(request,OtherConfig.getKataTerlarang());
                if(!XSSFilteringManual(rawRequest,request,response)){
                    /**
                     * Saat masuk sudah otomatis dari boddy nya error
                     * jadi wrapper yang mentah nya di switch ke object request agar bisa di stop prosesing nya
                     * karena kombinasi hacker untuk memasukkan script bisa di path variable saja atau req param saja , atau body saja
                     * agar program tidak terkecoh oleh kombinasi tersebut , maka sediakan 1 object request mentah nya agar bisa didapat kan script hacker tersebut
                     * dan dimasukkan ke dalam log
                     */
                    request = rawRequest;
                    xssResponse.setStatus(HttpStatus.FORBIDDEN.value());
                    xssResponse.setMessage("XSS attack error");
                    response.getWriter().write(convertObjectToJson(xssResponse));
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    strExceptionArr[1] = "doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain) "+ RequestCapture.allRequest(request);
                    LoggingFile.exceptionStringz(strExceptionArr, new XSSAttackExcception("Serangan Hacker"), OtherConfig.getFlagLoging());
                    return;
                }
            }
            request = rawRequest;

            /**
             Langkah pertama otentikasi token
             */
//            authorization = Crypto.performDecrypt("9f33a5c2b32fd796d6ab7cd33e762d92dc6f57c6d691127c2f212265a63bdc47fb9c44d8f1cb61b46a1a6898dd72bd28173e79c8e1bed2e5cfaf17dc9c9c6064fc6441f20be9e6ce4482cfa22ef1707dd88e4b233803793f79d5df8ff87b31847eea122f73d50718cab1151bcbfec545bab52fe08ed14abed83250d9360088fd594a55b687a66bf9a4e4691f8205a3ddd88339422a2a1dcf7f2a867b505a180d7ce612c15d70abd4b522dff66d3ab5c77fa5a093319b90af931c8ddda66b2f260c3b7d2ed5582b07f8c311f0c3734b98f2f38e20fd76d0393f64069b4f3cce541f75689ae61de417059b20f6b8b0e4b913ee90e28e58ebc008cdb74ec479e90c4ed5a9d16edcfe7a0ac402b3975d6976");
            if(!"".equals(authorization) && authorization.startsWith("Bearer ") && authorization.length()>7)
            {
                token = authorization.substring(7);//memotong setelah kata Bearer+spasi = 7 digit

                /**
                 *  DECRYPT TOKEN DARI FRONT END
                 */
                token = Crypto.performDecrypt(token);
                userName = jwtUtility.getUsernameFromToken(token);
                if(userName != null &&
                        SecurityContextHolder.getContext().getAuthentication()==null)
                {
                    if(jwtUtility.validateToken(token))
                    {

                        /**
                         Disini dicek ulang token ke table user apakah valid atau tidak user tersebut.
                         Karena payload di JWT base64 , orang dapat merangkai nya secara manual
                         jadi kalau pilihan nya security maka perlu diverifikasi lagi ke database informasi yang ada di JWT itu valid atau tidak
                         secara performance menurun karena ada step harus membuka koneksi ke database
                         akan tetapi lebih aman kalau divalidasi 2 kali...
                         */
                        final UserDetails userDetails = usersService.loadUserByUsername(userName);
                        final UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null,
                                        userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        catch (XSSParamException e)
        {
            xssResponse.setStatus(HttpStatus.FORBIDDEN.value());
            xssResponse.setMessage("XSS attack error");
            response.getWriter().write(convertObjectToJson(xssResponse));
            response.setStatus(HttpStatus.FORBIDDEN.value());
            strExceptionArr[1] = "doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain) "+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
        }
        catch (Exception ex)
        {
            strExceptionArr[1] = "doFilterInternal(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain) "+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, ex, OtherConfig.getFlagLoging());
        }

        filterChain.doFilter(request,response);
    }

    private Boolean XSSFilteringManual(RawHttpServletRequestWrapper requestWrapper,
                                       HttpServletRequest request,
                                       HttpServletResponse response
    )throws IOException,ServletException{
        try {

            String uri = requestWrapper.getRequestURI();
            String decodedURI = URLDecoder.decode(uri, "UTF-8");

            // XSS:  Path Variable Validation
            if (!XSSValidationUtils.isValidURL(decodedURI, OtherConfig.getKataTerlarang())) {
                return false;
            }
            if (!StringUtils.isEmpty(requestWrapper.getBody())) {
                // XSS :  Request Body validation
                if (!XSSValidationUtils.isValidURLPattern(requestWrapper.getBody(), OtherConfig.getKataTerlarang())) {
                    return false;
                }
            }
        } catch (XSSAttackExcception ex) {
            strExceptionArr[1] = "XSSFilteringManual(MyHttpServletRequestWrapper requestWrapper, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)"+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, ex, OtherConfig.getFlagLoging());
            return false;
        }  catch (Exception ex) {
            strExceptionArr[1] = "XSSFilteringManual(MyHttpServletRequestWrapper requestWrapper, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)"+ RequestCapture.allRequest(request);
            LoggingFile.exceptionStringz(strExceptionArr, ex, OtherConfig.getFlagLoging());
            return false;
        }

        return true;
    }
    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}