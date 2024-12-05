package com.alpha.olsp.mapper;

import com.alpha.olsp.dto.response.OrderResponseDto;
import com.alpha.olsp.dto.response.OrderItemResponseDto;
import com.alpha.olsp.model.Order;
import com.alpha.olsp.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponseDto toOrderResponseDto(Order order);

    List<OrderItemResponseDto> toOrderItemResponseDtos(List<OrderItem> items);
}
