package com.baoht.ecommercebackend.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MapUtils {

    private static ModelMapper modelMapper = new ModelMapper();

    public static <S, T> List<T> mapPage(final Page<S> source,
                                         Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static <S, T> List<T> mapList(final Collection<S> source,
                                         Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public static <S, T> T map(final S entity, Class<T> targetClass) {
        return modelMapper.map(entity, targetClass);
    }


}
