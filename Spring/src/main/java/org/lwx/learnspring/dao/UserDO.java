package org.lwx.learnspring.dao;

import lombok.Data;

@Data
public class UserDO {
    private Integer id;
    private String username;
    private String password;
}
