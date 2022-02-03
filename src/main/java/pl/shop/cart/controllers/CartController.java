package pl.shop.cart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.shop.cart.service.ICartService;
import pl.shop.cart.session.SessionObject;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/add/{itemId}")
    public String addItemToCart(@PathVariable Integer itemId) {
        this.cartService.addItemToCart(itemId);
        return "redirect:/main";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart",
                this.sessionObject.getCart());
        model.addAttribute("sum", this.sessionObject.getCart().getSum());
        model.addAttribute("logged", this.sessionObject.isLogged());

        return "cart";
    }
}
