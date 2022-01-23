package pl.shop.cart.service;

import pl.shop.cart.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}
