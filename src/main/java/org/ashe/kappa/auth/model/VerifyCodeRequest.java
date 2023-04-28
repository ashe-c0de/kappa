package org.ashe.kappa.auth.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyCodeRequest {

    private String email;

    private String verifyCode;

}
