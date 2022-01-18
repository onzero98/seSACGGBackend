package com.sesac.vo;

import lombok.Data;

@Data
public class RequestUser {
    private String email;
    private String userId;
    private String password;
    private String nickName;
}
