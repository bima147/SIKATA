package coid.bcafinance.bpsringbootfinal.service;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 10:37
@Last Modified 05/03/2024 10:37
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.configuration.OtherConfig;
import coid.bcafinance.bpsringbootfinal.core.IService;
import coid.bcafinance.bpsringbootfinal.dto.SearchParamDTO;
import coid.bcafinance.bpsringbootfinal.dto.user.management.GroupMenuDTO;
import coid.bcafinance.bpsringbootfinal.handler.RequestCapture;
import coid.bcafinance.bpsringbootfinal.handler.ResponseHandler;
import coid.bcafinance.bpsringbootfinal.model.GroupMenu;
import coid.bcafinance.bpsringbootfinal.repo.GroupMenuRepo;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

/**
 *  Company Code - Not Necessery
 *  Modul Code 01
 *  Type of Error -> Validation = FV , Engine Error = FE
 *  ex : FE01001 (Error di Modul GroupMenu Functional Save)
 */
@Service
@Transactional
public class GroupMenuService implements IService<GroupMenu> {
    @Autowired
    private GroupMenuRepo groupMenuRepo;

    @Autowired
    private ModelMapper modelMapper;

    Map<String,Object> mapColum = new HashMap<>();

    TransformToDTO transformToDTO = new TransformToDTO();
    private List<SearchParamDTO> listSearchParamDTO  = new ArrayList<>();

    Map<String,Object> mapResult = new HashMap<>();

    private String [] strExceptionArr = new String[2];

    public GroupMenuService() {
        strExceptionArr[0] = "GroupMenuService";
        forComponentFiltering();
    }

    private void forComponentFiltering()
    {
        listSearchParamDTO.add(new SearchParamDTO("nama","Nama"));
    }

    public Integer check(String name) {
        Integer data = groupMenuRepo.findTopByGroupMenuName(name).size();
        return data;
    }

    @Override
    public ResponseEntity<Object> save(GroupMenu groupMenu,
                                       HttpServletRequest request) {
        if(groupMenu==null)
        {
            return new ResponseHandler().generateResponse("Data Tidak Valid",
                    HttpStatus.BAD_REQUEST,
                    null,
                    "FV01001", request);//FAILED VALIDATION
        }
        /*
            validasi ke 2
            FV01002
         */
        try{
            /*
                Audit Trails untuk id User nanti disini
                groupMenu.setCreatedBy(1L); // id dari token JWT
             */
            groupMenuRepo.save(groupMenu);
        }catch (Exception e)
        {
            strExceptionArr[1] = "save(GroupMenu groupMenu, HttpServletRequest request) LINE 55"+ RequestCapture.allRequest(request) ;
            LoggingFile.exceptionStringz(strExceptionArr, e, OtherConfig.getFlagLoging());
            return new ResponseHandler().generateResponse("Data Gagal Disimpan !! ",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null,
                    "FE01001", request);//FAILED ERROR
        }


        /*
            FE01002
         */

        return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                HttpStatus.CREATED,
                null,
                null, request);
    }

    @Override
    public ResponseEntity<Object> saveBatch(List<GroupMenu> lt, HttpServletRequest request) {
        groupMenuRepo.saveAll(lt);

        return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                HttpStatus.CREATED,
                null,
                null, request);
    }

    @Override
    public ResponseEntity<Object> edit(Long id, GroupMenu groupMenu, HttpServletRequest request) {

        Optional<GroupMenu> optionalGroupMenu = groupMenuRepo.findById(id);
        if(optionalGroupMenu.isEmpty())
        {
            return new ResponseHandler().generateResponse("Data Tidak Ditemukan",
                    HttpStatus.BAD_REQUEST,
                    null,
                    "FV01021", request);//FAILED VALIDATION
        }
        GroupMenu gM = optionalGroupMenu.get();
        gM.setGroupMenuName(groupMenu.getGroupMenuName());
        gM.setUpdatedAt(new Date());
        gM.setUpdatedBy(1L);

        GroupMenuDTO groupMenuDTO =
                modelMapper.map(gM, new TypeToken<GroupMenuDTO>() {}.getType());

        return new ResponseHandler().generateResponse("Berhasil Disimpan!!",
                HttpStatus.CREATED,
                groupMenuDTO,
                null, request);
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
    public ResponseEntity<Object> find(Pageable pageable,
                                       String columFirst,
                                       String valueFirst,
                                       HttpServletRequest request) {
        Page<GroupMenu> pageGroupMenu = null;
        List<GroupMenu> listGroupMenu = null;

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

        pageGroupMenu = getDataByValue(pageable,columFirst,valueFirst);
        listGroupMenu = pageGroupMenu.getContent();
        if(listGroupMenu.isEmpty())
        {
            return new ResponseHandler().
                    generateResponse("DATA TIDAK DITEMUKAN",
                            HttpStatus.NOT_FOUND,
                            null,//perubahan 21-12-2023
                            "X-99-002",
                            request);
        }
        List<GroupMenuDTO>  ltGroupMenuDTO =
                modelMapper.map(listGroupMenu, new TypeToken<List<GroupMenuDTO>>() {}.getType());
        mapResult = transformToDTO.transformObject(mapResult,
                ltGroupMenuDTO,
                pageGroupMenu,
                columFirst,
                valueFirst,
                listSearchParamDTO);

        return  new ResponseHandler().
                generateResponse("OK",
                        HttpStatus.OK,
                        mapResult,
                        null,
                        request);
    }

    @Override
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }


    private Page<GroupMenu> getDataByValue(Pageable pageable, String columnFirst, String valueFirst)
    {
        if(valueFirst.equals("") || valueFirst==null)
        {
            return groupMenuRepo.findByGroupMenuID(pageable,valueFirst);
        }
        if(columnFirst.equals("id"))
        {
            return groupMenuRepo.findByGroupMenuID(pageable,valueFirst);
        } else if (columnFirst.equals("nama")) {
            return groupMenuRepo.findByGroupMenuNameContainingIgnoreCase(pageable,valueFirst);
        }

        return groupMenuRepo.findByGroupMenuID(pageable,valueFirst);// ini default kalau parameter search nya tidak sesuai--- asumsi nya di hit bukan dari web
    }
}