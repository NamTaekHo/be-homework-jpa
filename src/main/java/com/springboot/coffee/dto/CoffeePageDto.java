package com.springboot.coffee.dto;

import com.springboot.order.dto.OrderResponseDto;
import com.springboot.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoffeePageDto {
    private List<OrderResponseDto> data;

    private PageInfo pageInfo;

    @Getter
    @AllArgsConstructor
    public static class PageInfo{
        private int page;
        private int size;
        private int totalElements;
        private int totalPages;
    }
}
