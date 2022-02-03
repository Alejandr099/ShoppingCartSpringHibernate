package pl.shop.cart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.shop.cart.service.IOrderService;
import pl.shop.cart.session.SessionObject;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Autowired
    IOrderService orderService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order() {
        this.orderService.confirmOrder();
        return "redirect:/main";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model) {
        model.addAttribute("orders", this.orderService.getOrdersForCurrentUser());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "orders";
    }
}
