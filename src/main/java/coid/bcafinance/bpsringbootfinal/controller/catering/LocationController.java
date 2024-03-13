package coid.bcafinance.bpsringbootfinal.controller.catering;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 12/03/2024 8:38
@Last Modified 12/03/2024 8:38
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.dto.location.LocationDTO;
import coid.bcafinance.bpsringbootfinal.model.Location;
import coid.bcafinance.bpsringbootfinal.service.LocationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @Autowired
    private ModelMapper modelMapper;

    private Map<String,String> mapSorting = new HashMap<String,String>();

    public LocationController() {
        mapSorting();
    }

    private void mapSorting()
    {
        mapSorting.put("id","locationID");
    }

    @PostMapping("/v1/add")
    public ResponseEntity<Object> save(@Valid @RequestBody LocationDTO locationDTO,
                                       HttpServletRequest request){
        Location location = modelMapper.map(locationDTO, new TypeToken<Location>() {}.getType());
        return locationService.save(location,request);
    }

    @GetMapping("/v1/find-detail/{id}")
    public ResponseEntity<Object> edit(@PathVariable(value = "id") Long id,
                                       HttpServletRequest request){
        return locationService.findById(id,request);
    }

    @PutMapping("/v1/edit/{id}")
    public ResponseEntity<Object> edit(@Valid @RequestBody LocationDTO locationDTO,
                                       @PathVariable(value = "id") Long id,
                                       HttpServletRequest request){
        Location location =
                modelMapper.map(locationDTO, new TypeToken<Location>() {}.getType());
        return locationService.edit(id,location,request);
    }

    @GetMapping("/v1/list/{page}/{sort}/{sort-by}")
    public ResponseEntity<Object> find(
            @PathVariable(value = "page") Integer page,//page yang ke ?
            @PathVariable(value = "sort") String sort,//asc desc
            @PathVariable(value = "sort-by") String sortBy,// column Name in java Variable,
            @RequestParam("filter-by") String filterBy,
            @RequestParam("value") String value,
            @RequestParam("size") String size,
            HttpServletRequest request
    ){
        try {
            Pageable pageable = null;
            page = page==null?0:page;
            sortBy = (sortBy==null || sortBy.equals(""))?"id":sortBy;//penanda kalau null dari FE itu berarti kayak buka menu baru
            sort   = (sort==null || sort.equals("") || sort.equals("asc"))?"asc":"desc";// else = asc, karena bisa jadi dari FE dikirim bukan asc, walaupun sudah dijaga null value
            filterBy = mapSorting.get(filterBy);
            filterBy   = (filterBy==null || filterBy.equals("") || filterBy.equals("id"))?"cateringID":filterBy;// else = asc, karena bisa jadi dari FE dikirim bukan asc, walaupun sudah dijaga null value

            sortBy = mapSorting.get(sortBy);// id = locationID, nama = province dst....
//        pageable = PageRequest.of(page,Integer.parseInt(size.equals("")?"10":size));
            pageable = PageRequest.of(page,Integer.parseInt(size.equals("")?"10":size),
                    sort.equals("desc")? Sort.by(sortBy).descending():Sort.by(sortBy));
            return locationService.find(pageable,filterBy,value,request);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id,
                                         HttpServletRequest request){
        try {
            return locationService.delete(id, request);
        } catch (Exception e) {
            return null;
        }
    }
}
