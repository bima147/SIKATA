package coid.bcafinance.bpsringbootfinal.service;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 12/03/2024 8:42
@Last Modified 12/03/2024 8:42
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.configuration.OtherConfig;
import coid.bcafinance.bpsringbootfinal.core.IService;
import coid.bcafinance.bpsringbootfinal.dto.location.LocationDTO;
import coid.bcafinance.bpsringbootfinal.handler.RequestCapture;
import coid.bcafinance.bpsringbootfinal.handler.ResponseHandler;
import coid.bcafinance.bpsringbootfinal.model.Location;
import coid.bcafinance.bpsringbootfinal.repo.LocationRepo;
import coid.bcafinance.bpsringbootfinal.util.LoggingFile;
import coid.bcafinance.bpsringbootfinal.util.TransformToDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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
 *  Modul Code 03
 *  Type of Error -> Validation = FV , Engine Error = FE
 *  ex : FV02001 (Error di Modul GroupMenu Functional Save)
 */
@Service
@Transactional
public class LocationService implements IService<Location> {
    @Autowired
    private LocationRepo locationRepo;

    @Autowired
    private ModelMapper modelMapper;

    private Location location = null;

    private String [] strExceptionArr = new String[2];

    Map<String,Object> mapColum = new HashMap<>();

    TransformToDTO transformToDTO = new TransformToDTO();

    Map<String,Object> mapResult = new HashMap<>();

    public LocationService(LocationRepo locationRepo) {
        strExceptionArr[0] = "LocationService";
        mapColum.put("id","locationID");
    }

    @Override
    public ResponseEntity<Object> save(Location location,
                                       HttpServletRequest request) {
        if(location == null)
        {
            return new ResponseHandler().generateResponse("Data Tidak Valid",
                    HttpStatus.BAD_REQUEST,
                    null,
                    "FV03001", request);//FAILED VALIDATION
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
            LocationDTO saved =
                    modelMapper.map(locationRepo.save(location), new TypeToken<LocationDTO>() {}.getType());
            return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                    HttpStatus.CREATED,
                    saved,
                    null, request);
        } catch (Exception e)
        {
            strExceptionArr[1] = "save(Location location, HttpServletRequest request) LINE 68"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE03001", request);//FAILED ERROR
        }
    }

    @Override
    public ResponseEntity<Object> saveBatch(List<Location> lt, HttpServletRequest request) {
        if(lt.size() == 0 || lt.isEmpty())
        {
            return new ResponseHandler().generateResponse("Data Tidak Valid",
                    HttpStatus.BAD_REQUEST,
                    null,
                    "FV03002", request);//FAILED VALIDATION
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
            LocationDTO saved =
                    modelMapper.map(locationRepo.saveAll(lt), new TypeToken<LocationDTO>() {}.getType());
            return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                    HttpStatus.CREATED,
                    saved,
                    null, request);
//            locationRepo.save(catering);
        }catch (Exception e)
        {
            strExceptionArr[1] = "saveBatch(List<Location> lt, HttpServletRequest request) LINE 105"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE03002", request);//FAILED ERROR
        }
    }

    @Override
    public ResponseEntity<Object> edit(Long id, Location location, HttpServletRequest request) {
        try {
            Optional<Location> optionalLocation = locationRepo.findById(id);
            if(optionalLocation.isEmpty())
            {
                return new ResponseHandler().generateResponse("Data Tidak Ditemukan",
                        HttpStatus.BAD_REQUEST,
                        null,
                        "FV03003", request);//FAILED VALIDATION
            }
            Location gM = optionalLocation.get();
            gM.setCity(location.getCity());
            gM.setProvince(location.getProvince());
            gM.setLatitude(location.getLatitude());
            gM.setLongitude(location.getLongitude());
            gM.setLimit(location.getLimit());
            gM.setUpdatedAt(new Date());
            gM.setUpdatedBy(1L);

            LocationDTO locationDTO =
                    modelMapper.map(gM, new TypeToken<LocationDTO>() {}.getType());

            return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                    HttpStatus.CREATED,
                    locationDTO,
                    null, request);
        } catch (Exception e) {
            strExceptionArr[1] = "edit(Long id, Location location, HttpServletRequest request) LINE 140"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE03003", request);//FAILED ERROR
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id, HttpServletRequest request) {
        try {
            Optional<Location> check = locationRepo.findById(id);
            if (check.isEmpty()) {
                return new ResponseHandler().generateResponse("Data Tidak Ditemukan",
                        HttpStatus.BAD_REQUEST,
                        null,
                        "FV03004", request);//FAILED VALIDATION
            }
            locationRepo.deleteById(id);
            return new ResponseHandler().generateResponse("Berhasil Dihapus!!",
                    HttpStatus.ACCEPTED,
                    null,
                    null, request);
        } catch (Exception e) {
            strExceptionArr[1] = "delete(Long id, HttpServletRequest request) LINE 177"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Dihapus !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE03004", request);//FAILED ERROR
        }
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
