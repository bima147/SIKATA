package coid.bcafinance.bpsringbootfinal.util;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 08/03/2024 10:34
@Last Modified 08/03/2024 10:34
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.dto.user.management.menu.MenuResponse3DTO;
import coid.bcafinance.bpsringbootfinal.model.Menu;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformationData {
    private int intListMenuSize = 0;
    private StringBuilder sBuild = new StringBuilder();
    private String strAppend = "";
    private String strInisial = "";
    private Map<String,Object> mapMenu = new HashMap<>();
    private final List<Object> lObj = new ArrayList<>();
    private ModelMapper modelMapper;

    PropertyMap<Menu, MenuResponse3DTO> propMapMenuToLogin;
    public TransformationData() {
        this.modelMapper = new ModelMapper();
        propMapMenuToLogin = new PropertyMap<Menu,MenuResponse3DTO>() {
            protected void configure() {
                map().setNamaGroupMenu(source.getGroupMenu().getGroupMenuName());
            }
        };
        modelMapper.addMappings(propMapMenuToLogin);
    }

//    public Map<String, List<MenuResponse3DTO>> doTransformAksesMenuLogin(List<Menu> listMenu){
//        lObj.clear();
//        intListMenuSize = listMenu.size();
//        /**
//         * Transform dari Object Menu ke MenuResponse3DTO untuk Menghilangkan field2 yang tidak perlu dibawa ke FE
//         */
//        List<MenuResponse3DTO> listMapAksesMenuDTO = modelMapper.map(listMenu,
//                new TypeToken<List<MenuResponse3DTO>>() {}.getType());
//        /**
//         * Grouping berdasarkan field getNamaGroupMenu
//         */
//        Map<String, List<MenuResponse3DTO>> map = groupBy(listMapAksesMenuDTO, MenuResponse3DTO::getNamaGroupMenu);
//        lObj.add(map);
//        return map;
//    }

    public List<Object> doTransformAksesMenuLogin(List<Menu> listMenu){
        lObj.clear();
        intListMenuSize = listMenu.size();
        /**
         * Transform dari Object Menu ke MenuResponse3DTO untuk Menghilangkan field2 yang tidak perlu dibawa ke FE
         */
        List<MenuResponse3DTO> listMapAksesMenuDTO = modelMapper.map(listMenu,
                new TypeToken<List<MenuResponse3DTO>>() {}.getType());
        /**
         * Grouping berdasarkan field getNamaGroupMenu
         */
        Map<String, List<MenuResponse3DTO>> map = groupBy(listMapAksesMenuDTO, MenuResponse3DTO::getNamaGroupMenu);
        Map<String ,Object> map2 = null;
        for (Map.Entry<String,List<MenuResponse3DTO>> x:
                map.entrySet()) {
            map2 = new HashMap<>();
            map2.put("group",x.getKey());
            map2.put("subMenu",x.getValue());

            lObj.add(map2);
        }
        return lObj;
    }

    public <E, K> Map<K, List<E>> groupBy(List<E> list, Function<E, K> keyFunction) {
        return Optional.ofNullable(list)
                .orElseGet(ArrayList::new)
                .stream().skip(1)
                .collect(Collectors.groupingBy(keyFunction,Collectors.toList()));
    }
}
