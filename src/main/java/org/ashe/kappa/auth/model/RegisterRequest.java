package org.ashe.kappa.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {


    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
