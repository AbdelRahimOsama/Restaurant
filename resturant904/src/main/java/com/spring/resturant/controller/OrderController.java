package com.spring.resturant.controller;

import com.spring.resturant.controller.vm.OrderResponse;
import com.spring.resturant.controller.vm.RequestOrderVm;
import com.spring.resturant.dto.OrderDto;
import com.spring.resturant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor()
@CrossOrigin("http://localhost:4200")
public class OrderController {

    final private OrderService orderService;

    @PostMapping("save")
    public ResponseEntity<Void> saveOrders( @RequestBody RequestOrderVm requestOrder) {
        orderService.addOrders(requestOrder.getOrderItemDtos(),requestOrder.getCustomerId(),requestOrder.getTotalNumber()
                ,requestOrder.getTotalPrice(),requestOrder.getTableNumber());
        return ResponseEntity.ok().build();
    }

    @GetMapping("myorder/{customerId}/pageNum/{pageNumber}/pageSize/{pageSize}")
    public ResponseEntity<OrderResponse> getMyOrders(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId,pageNumber,pageSize));
    }

    @GetMapping("/pageNum/{pageNumber}/pageSize/{pageSize}")
    public ResponseEntity<OrderResponse> geAllOrders(@PathVariable int pageNumber, @PathVariable int pageSize) {
        return ResponseEntity.ok(orderService.getAllOrders(pageNumber,pageSize));
    }

}
