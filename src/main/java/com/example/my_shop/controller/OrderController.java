package com.example.my_shop.controller;
//import com.example.my_shop.entity.model.Order;
import com.example.my_shop.entity.model.Order;
import com.example.my_shop.entity.model.User;
import com.example.my_shop.entity.repository.OrderRepository;
//import com.example.my_shop.service.CartService;
import com.example.my_shop.service.OrderService;
import com.example.my_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@Slf4j

public class OrderController {

    private final UserService userService;

    private final OrderService orderService;

    @GetMapping("/order")
    public String getOrder(Principal principal, Model model) {
        User userFromDB = userService.findByUsername(principal.getName());
        model.addAttribute("products", userFromDB.getProductList());

        return "order/order";
    }

    @PostMapping("/order")
    public String postOrder(
            Principal principal,
            @Valid Order validOrder,
            BindingResult bindingResult,
            Model model
    ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = UtilController.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("perfumes", user.getProductList());

            return "order/order";
        } else {
            order.getProducts().addAll(user.getProductList());
            order.setTotalPrice(validOrder.getTotalPrice());
            order.setFirstName(validOrder.getFirstName());
            order.setLastName(validOrder.getLastName());
            order.setAddress(validOrder.getAddress());
            order.setPayment(validOrder.getPayment());

            user.getProductList().clear();

            orderService.save(order);

            log.debug("User {} id={} made an order: FirstName={}, LastName={}, TotalPrice={} " +
                            "Address={}, payment={}",
                    user.getUsername(), user.getId(), order.getFirstName(), order.getLastName(), order.getTotalPrice(),
                    order.getAddress(), order.getPayment());
        }
        return "redirect:/finalizeOrder";

    }

    @GetMapping("/finalizeOrder")
    public String finalizeOrder(Model model) {
        List<Order> orderList = orderService.findAll();
        Order orderIndex = orderList.get(orderList.size() - 1);
        model.addAttribute("orderIndex", orderIndex.getId());

        return "order/finalizeOrder";
    }
    @GetMapping("/userOrders")
    public String getUserOrders(Principal principal, Model model) {
        User userFromDB = userService.findByUsername(principal.getName());
        List<Order> orders = orderService.findOrderByUser(userFromDB);
        model.addAttribute("orders", orders);

        return "order/orders";
    }

    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "order/orders";
    }
}
