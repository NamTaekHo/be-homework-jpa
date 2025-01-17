package com.springboot.order.mapper;

import com.springboot.coffee.entity.Coffee;
import com.springboot.member.entity.Member;
import com.springboot.member.service.MemberService;
import com.springboot.order.dto.OrderCoffeeDto;
import com.springboot.order.dto.OrderPatchDto;
import com.springboot.order.dto.OrderPostDto;
import com.springboot.order.dto.OrderResponseDto;
import com.springboot.order.entity.Order;
import com.springboot.order.entity.OrderCoffee;
import org.mapstruct.Mapper;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
//    private final MemberService memberService;
    Order orderPostDtoToOrder(OrderPostDto orderPostDto);
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
    OrderResponseDto orderToOrderResponseDto(Order order);
    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);

//    default Order orderPostDtoToOrder(OrderPostDto orderPostDto){
//        Order order = new Order();
//        Member member = new Member();
//        member.setMemberId(orderPostDto.getMemberId());
//        order.setMember(member);
//
//        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees().stream().map(
//                orderCoffeeDto -> {
//                    OrderCoffee orderCoffee = new OrderCoffee();
//                    Coffee coffee = new Coffee();
//                    coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
//                    orderCoffee.setCoffee(coffee);
//
//                    orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
//                    orderCoffee.setOrder(order);
//                    return orderCoffee;
//                }
//        ).collect(Collectors.toList());
//        order.setOrderCoffees(orderCoffees);
//    }
}
