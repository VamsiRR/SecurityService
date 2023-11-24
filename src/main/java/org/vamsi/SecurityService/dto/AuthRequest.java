package org.vamsi.SecurityService.dto;

import java.io.Serializable;

public record AuthRequest(String userName, String password) implements Serializable {

    @Override
    public String toString() {
        return "AuthRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
