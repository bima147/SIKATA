package coid.bcafinance.bpsringbootfinal.controller.user.management;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 05/03/2024 10:44
@Last Modified 05/03/2024 10:44
Version 1.0
*/


import coid.bcafinance.bpsringbootfinal.dto.user.management.GroupMenuDTO;
import coid.bcafinance.bpsringbootfinal.handler.ResponseHandler;
import coid.bcafinance.bpsringbootfinal.model.GroupMenu;
import coid.bcafinance.bpsringbootfinal.service.GroupMenuService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usr-mgmnt")
public class GroupMenuController {
    @Autowired
    private GroupMenuService groupMenuService;

    @Autowired
    private ModelMapper modelMapper;

    private Map<String,String> mapSorting = new HashMap<String,String>();

    public GroupMenuController() {
        mapSorting();
    }

    private void mapSorting()
    {
        mapSorting.put("id","idGroupMenu");
        mapSorting.put("nama","namaGroupMenu");
    }

    @PostMapping("/v1/group-menu")
    public ResponseEntity<Object> save(@Valid @RequestBody GroupMenuDTO groupMenuDTO,
                                       HttpServletRequest request){
        Integer cek = groupMenuService.check(groupMenuDTO.getGroupMenuName());
        if(cek != 0) {
            return new ResponseHandler().generateResponse("Gagal disimpan, nama group menu telah tersedia!",
                    HttpStatus.CONFLICT,
                    null,
                    "FV01001",
                    request);
        }
        GroupMenu groupMenu = modelMapper.map(groupMenuDTO, new TypeToken<GroupMenu>() {}.getType());
        return groupMenuService.save(groupMenu,request);
    }

    @PostMapping("/v1/group-menu/sb")
    public ResponseEntity<Object> saveBatch(@Valid @RequestBody List<GroupMenuDTO> ltGroupMenuDTO,
                                            HttpServletRequest request){
        List<GroupMenu>  ltGroupMenu =
                modelMapper.map(ltGroupMenuDTO, new TypeToken<List<GroupMenu>>() {}.getType());

        return groupMenuService.saveBatch(ltGroupMenu,request);
    }

    @PutMapping("/v1/group-menu/{id}")
    public ResponseEntity<Object> edit(@Valid @RequestBody GroupMenuDTO groupMenuDTO,
                                       @PathVariable(value = "id") Long id,
                                       HttpServletRequest request){
        GroupMenu  groupMenu =
                modelMapper.map(groupMenuDTO, new TypeToken<GroupMenu>() {}.getType());
        return groupMenuService.edit(id,groupMenu,request);
    }

    @DeleteMapping("/v1/group-menu/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){

        return null;
    }

    @GetMapping("/v1/group-menu/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id){
        return null;
    }

    @GetMapping("/v1/group-menu/{page}/{sort}/{sort-by}")
    public ResponseEntity<Object> find(
            @PathVariable(value = "page") Integer page,//page yang ke ?
            @PathVariable(value = "sort") String sort,//asc desc
            @PathVariable(value = "sort-by") String sortBy,// column Name in java Variable,
            @RequestParam("filter-by") String filterBy,
            @RequestParam("value") String value,
            @RequestParam("size") String size,
            HttpServletRequest request
    ){
        Pageable pageable = null;
        page = page==null?0:page;
        sortBy = (sortBy==null || sortBy.equals(""))?"id":sortBy;//penanda kalau null dari FE itu berarti kayak buka menu baru
        sort   = (sort==null || sort.equals("") || sort.equals("asc"))?"asc":"desc";// else = asc, karena bisa jadi dari FE dikirim bukan asc, walaupun sudah dijaga null value

        sortBy = mapSorting.get(sortBy);// id = idGroupMenu, nama = namaGroupMenu dst....
        pageable = PageRequest.of(page,Integer.parseInt(size.equals("")?"10":size),
                sort.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy));
        return groupMenuService.find(pageable,filterBy,value,request);
    }

    @PostMapping("/v1/group-menu/csv")
    public ResponseEntity<Object> dataToExport(MultipartFile multipartFile, HttpServletRequest request) {
        return null;
    }
}