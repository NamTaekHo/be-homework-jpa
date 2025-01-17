package com.springboot.order.mapper;


import com.springboot.coffee.service.CoffeeService;
import com.springboot.member.service.MemberService;
import com.springboot.order.dto.*;
import com.springboot.order.entity.Order;
import com.springboot.order.entity.OrderCoffee;
import com.springboot.order.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperImpl1 implements OrderMapper {
    private final MemberService memberService;
    private final CoffeeService coffeeService;
    private final OrderService orderService;

    public OrderMapperImpl1(MemberService memberService, CoffeeService coffeeService, OrderService orderService) {
        this.memberService = memberService;
        this.coffeeService = coffeeService;
        this.orderService = orderService;
    }

    // orderCoffeeDto를 OrderCoffee로 변환, OrderPostDto의 리스트를 엔티티의 리스트로 바꿔줌
    public OrderCoffee orderCoffeeDtoToOrderCoffee(OrderCoffeeDto dto, Order order){
        OrderCoffee orderCoffee = new OrderCoffee();
        orderCoffee.setCoffee(coffeeService.findVerifiedCoffee(dto.getCoffeeId()));
        orderCoffee.setQuantity(dto.getQuantity());
        orderCoffee.setOrder(order);
        return orderCoffee;
    }

    // 혹시 안되면 Coffees로 바꿔보셈
    @Override
    public Order orderPostDtoToOrder(OrderPostDto dto) {
        Order order = new Order();
        order.setOrderCoffees(dto.getOrderCoffees().stream()
                .map(orderCoffeeDto -> orderCoffeeDtoToOrderCoffee(orderCoffeeDto, order))
                .collect(Collectors.toList()));
        order.setMember(memberService.findVerifiedMember(dto.getMemberId()));

        return order;
    }

    @Override
    public Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto) {
        return null;
    }

    @Override
    public OrderResponseDto orderToOrderResponseDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setOrderId(order.getOrderId());
        dto.setMemberId(order.getMember().getMemberId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setOrderCoffees(
                order.getOrderCoffees().stream().map(
                        orderCoffee -> orderCoffeeToOrderCoffeeResponseDto(orderCoffee)
                ).collect(Collectors.toList())
        );
        dto.setCreatedAt(order.getCreatedAt());

        return dto;
    }

    @Override
    public List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders) {
        return orders.stream().map(order -> orderToOrderResponseDto(order)).collect(Collectors.toList());
    }

    public OrderCoffeeResponseDto orderCoffeeToOrderCoffeeResponseDto(OrderCoffee orderCoffee){
        return new OrderCoffeeResponseDto(
                orderCoffee.getCoffee().getCoffeeId(),
                orderCoffee.getCoffee().getKorName(),
                orderCoffee.getCoffee().getEngName(),
                orderCoffee.getCoffee().getPrice(),
                orderCoffee.getQuantity()
        );
    }
}
