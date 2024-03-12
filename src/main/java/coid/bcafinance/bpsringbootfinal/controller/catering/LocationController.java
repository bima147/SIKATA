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

import coid.bcafinance.bpsringbootfinal.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
