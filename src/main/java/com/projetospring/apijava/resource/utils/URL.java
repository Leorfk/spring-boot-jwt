package com.projetospring.apijava.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String str){
        try {
            return URLDecoder.decode(str, "UTF-8");
        }catch (UnsupportedEncodingException e){
            return "";
        }
    }

    public static List<Integer> decodeList(String str){
/*        String[] vet = str.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<vet.length; i++){
            list.add(Integer.parseInt(vet[i]));
        }
        return list;
        Com expressão lambda*/
        return Arrays.asList(str.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}
