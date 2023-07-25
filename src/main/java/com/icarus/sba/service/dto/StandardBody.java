package com.icarus.sba.service.dto;

import com.icarus.sba.enums.ErrorCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class StandardBody<T> {

    private Integer code;

    private String message;

    private T data;

    @Builder.Default
    private Map<String, Object> metadata = new HashMap<>();

    public static <T> StandardBody<T> success(T data) {
        return build(ErrorCode.SUCCESS, data, null);
    }

    public static <T> StandardBody<List<T>> success(Page<T> page) {
        List<T> data = page.getContent();
        Map<String, Object> metadata = toMetadata(page);
        return build(ErrorCode.SUCCESS, data, metadata);
    }

    public static <T> StandardBody<T> failure(ErrorCode errorCode) {
        return build(errorCode, null, null);
    }

    public static <T> Map<String, Object> toMetadata(Page<T> page) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("total", page.getTotalElements());
        metadata.put("size", page.getSize());
        metadata.put("page", page.getNumber());
        metadata.put("totalPage", page.getTotalPages());
        Map<String, String> sort = page.getSort()
            .get()
            .collect(Collectors.toMap(Sort.Order::getProperty, i -> i.getDirection().name()));
        metadata.put("sort", sort);
        return metadata;
    }

    public static <T> StandardBody<T> build(
        ErrorCode errorCode,
        T data,
        Map<String, Object> metadata
    ) {
        return StandardBody.<T>builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .data(data)
            .metadata(metadata)
            .build();
    }

}
