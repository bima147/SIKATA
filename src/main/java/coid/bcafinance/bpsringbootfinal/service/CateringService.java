package coid.bcafinance.bpsringbootfinal.service;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 10:21
@Last Modified 05/03/2024 10:21
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.configuration.OtherConfig;
import coid.bcafinance.bpsringbootfinal.core.IService;
import coid.bcafinance.bpsringbootfinal.dto.SearchParamDTO;
import coid.bcafinance.bpsringbootfinal.dto.catering.CateringDTO;
import coid.bcafinance.bpsringbootfinal.dto.catering.CateringDetailDTO;
import coid.bcafinance.bpsringbootfinal.handler.RequestCapture;
import coid.bcafinance.bpsringbootfinal.handler.ResponseHandler;
import coid.bcafinance.bpsringbootfinal.model.Catering;
import coid.bcafinance.bpsringbootfinal.repo.CateringRepo;
import coid.bcafinance.bpsringbootfinal.util.LoggingFile;
import coid.bcafinance.bpsringbootfinal.util.TransformToDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *  Company Code - Not Necessery
 *  Modul Code 02
 *  Type of Error -> Validation = FV , Engine Error = FE
 *  ex : FV02001 (Error di Modul GroupMenu Functional Save)
 */
@Service
@Transactional
public class CateringService implements IService<Catering> {
    @Autowired
    private CateringRepo cateringRepo;

    @Autowired
    private ModelMapper modelMapper;

    private Catering catering = null;

    private String [] strExceptionArr = new String[2];

    Map<String,Object> mapColum = new HashMap<>();

    TransformToDTO transformToDTO = new TransformToDTO();

    private List<SearchParamDTO> listSearchParamDTO  = new ArrayList<>();

    Map<String,Object> mapResult = new HashMap<>();

    public CateringService(CateringRepo cateringRepo) {
        strExceptionArr[0] = "CateringService";
        mapColum.put("id","cateringID");
        mapColum.put("nama","name");
    }

    @Override
    public ResponseEntity<Object> save(Catering catering,
                                       HttpServletRequest request) {
        if(catering==null)
        {
            return new ResponseHandler().generateResponse("Data Tidak Valid",
                    HttpStatus.BAD_REQUEST,
                    null,
                    "FV02001", request);//FAILED VALIDATION
        }
        /*
            validasi ke 2
            FV02002
         */
        try{
            /*
                Audit Trails untuk id User nanti disini
                catering.setCreatedBy(1L); // id dari token JWT
             */
            CateringDTO saved =
                    modelMapper.map(cateringRepo.save(catering), new TypeToken<CateringDTO>() {}.getType());
            return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                    HttpStatus.CREATED,
                    saved,
                    null, request);
        } catch (Exception e)
        {
            strExceptionArr[1] = "save(Catering catering, HttpServletRequest request) LINE 69"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE02001", request);//FAILED ERROR
        }
    }

    @Override
    public ResponseEntity<Object> saveBatch(List<Catering> lt, HttpServletRequest request) {
        if(lt.size() == 0 || lt.isEmpty())
        {
            return new ResponseHandler().generateResponse("Data Tidak Valid",
                    HttpStatus.BAD_REQUEST,
                    null,
                    "FV02002", request);//FAILED VALIDATION
        }
        /*
            validasi ke 2
            FV02002
         */
        try{
            /*
                Audit Trails untuk id User nanti disini
                catering.setCreatedBy(1L); // id dari token JWT
             */
            CateringDTO saved =
                    modelMapper.map(cateringRepo.saveAll(lt), new TypeToken<CateringDTO>() {}.getType());
            return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                    HttpStatus.CREATED,
                    saved,
                    null, request);
//            cateringRepo.save(catering);
        }catch (Exception e)
        {
            strExceptionArr[1] = "saveBatch(List<Catering> lt, HttpServletRequest request) LINE 105"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE02002", request);//FAILED ERROR
        }
    }

    @Override
    public ResponseEntity<Object> edit(Long id, Catering catering, HttpServletRequest request) {
        try {
            Optional<Catering> optionalCatering = cateringRepo.findById(id);
            if(optionalCatering.isEmpty())
            {
                return new ResponseHandler().generateResponse("Data Tidak Ditemukan",
                        HttpStatus.BAD_REQUEST,
                        null,
                        "FV02003", request);//FAILED VALIDATION
            }
            Catering gM = optionalCatering.get();
            gM.setName(catering.getName());
            gM.setUpdatedAt(new Date());
            gM.setUpdatedBy(1L);

            CateringDTO cateringDTO =
                    modelMapper.map(gM, new TypeToken<CateringDTO>() {}.getType());

            return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                    HttpStatus.CREATED,
                    cateringDTO,
                    null, request);
        } catch (Exception e) {
            strExceptionArr[1] = "edit(Long id, Catering catering, HttpServletRequest request) LINE 140"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE02003", request);//FAILED ERROR
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Catering> check = cateringRepo.findById(id);
            if (check.isEmpty()) {
                return new ResponseHandler().generateResponse("Data Tidak Ditemukan",
                        HttpStatus.BAD_REQUEST,
                        null,
                        "FV02004", request);//FAILED VALIDATION
            }
            cateringRepo.deleteById(id);
            return new ResponseHandler().generateResponse("Berhasil Dihapus!!",
                    HttpStatus.ACCEPTED,
                    null,
                    null, request);
        } catch (Exception e) {
            strExceptionArr[1] = "delete(Long id, HttpServletRequest request) LINE 174"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Dihapus !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE02004", request);//FAILED ERROR
        }
    }

    @Override
    public ResponseEntity<Object> findById(Long id, HttpServletRequest request) {
        try {
            Optional<Catering> getData = cateringRepo.findById(id);
            if (getData.isEmpty()) {
                return new ResponseHandler().generateResponse("Data Tidak Ditemukan",
                        HttpStatus.BAD_REQUEST,
                        null,
                        "FV02005", request);//FAILED VALIDATION
            }
            CateringDetailDTO cateringDetailDTO =
                    modelMapper.map(getData, new TypeToken<CateringDetailDTO>() {}.getType());
            return new ResponseHandler().generateResponse("Berhasil Mendapatkan Data!!",
                    HttpStatus.OK,
                    cateringDetailDTO,
                    null, request);
        } catch (Exception e) {
            strExceptionArr[1] = "findById(Long id, HttpServletRequest request) LINE 199"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Didapatkan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE02005", request);//FAILED ERROR
        }
    }

    @Override
    public ResponseEntity<Object> find(Pageable pageable,
                                       String columFirst,
                                       String valueFirst,
                                       HttpServletRequest request) {
        Page<Catering> pageCatering = null;
        List<Catering> listCatering = null;

        if(columFirst.equals("id"))
        {
            if(!valueFirst.equals("") && valueFirst!=null)
            {
                try
                {
                        /*
                            UNTUK ID YANG BER TIPE NUMERIC
                            TIDAK PERLU DIGUNAKAN JIKA ID BER TIPE STRING
                         */
                    Long.parseLong(valueFirst);
                }
                catch (Exception e)
                {
//                    strExceptionArr[1] = "find(Pageable pageable, String columFirst, String valueFirst, HttpServletRequest request) --- LINE 252";
//                    LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
                    return new ResponseHandler().
                            generateResponse("DATA FILTER TIDAK SESUAI FORMAT HARUS ANGKA",
                                    HttpStatus.INTERNAL_SERVER_ERROR,
                                    null,//perubahan 21-12-2023
                                    "X-99-001",
                                    request);
                }
            }
        }

        pageCatering = getDataByValue(pageable,columFirst,valueFirst);
        listCatering = pageCatering.getContent();
        if(listCatering.isEmpty())
        {
            return new ResponseHandler().
                    generateResponse("DATA TIDAK DITEMUKAN",
                            HttpStatus.NOT_FOUND,
                            null,//perubahan 21-12-2023
                            "X-99-002",
                            request);
        }
        List<CateringDTO>  ltCateringDTO =
                modelMapper.map(listCatering, new TypeToken<List<CateringDTO>>() {}.getType());
        mapResult = transformToDTO.transformObject(mapResult,
                ltCateringDTO,
                pageCatering,
                columFirst,
                valueFirst,
                listSearchParamDTO);

        return  new ResponseHandler().
                generateResponse("OK",
                        HttpStatus.OK,
                        mapResult,
                        null,
                        request);

//        Page<Catering> pageAkses = null;
//        List<Catering> listAkses = null;
//        List<CateringDTO> listAkseDTO = null;
//
//        try {
//            pageAkses = getDataByValue(pageable,columFirst,valueFirst);
//            listAkses = pageAkses.getContent();
//            mapResult = transformToDTO.transformObject(mapResult,listAkses,pageAkses,columFirst,valueFirst);
//
//            return  new ResponseHandler().
//                    generateResponse("OK",
//                            HttpStatus.OK,
//                            mapResult,
//                            null,
//                            request);
//        } catch (Exception e) {
//            System.out.println(e);
//            return  new ResponseHandler().
//                    generateResponse("Errornya",
//                            HttpStatus.INTERNAL_SERVER_ERROR,
//                            e,
//                            null,
//                            request);
//        }
    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }


    private Page<Catering> getDataByValue(Pageable pageable, String columnFirst, String valueFirst)
    {
        if(valueFirst.equals("") || valueFirst==null)
        {
            return cateringRepo.findByCateringID(pageable,Long.parseLong(valueFirst));
        }
        if(columnFirst.equals("id"))
        {
            return cateringRepo.findByCateringID(pageable,Long.parseLong(valueFirst));
        } else if (columnFirst.equals("nama")) {
            return cateringRepo.findByNameContainingIgnoreCase(pageable,valueFirst);
        }

        return cateringRepo.findByCateringID(pageable,Long.parseLong(valueFirst));// ini default kalau parameter search nya tidak sesuai--- asumsi nya di hit bukan dari web
    }
}
