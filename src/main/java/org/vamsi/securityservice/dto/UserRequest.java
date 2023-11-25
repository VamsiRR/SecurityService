package org.vamsi.securityservice.dto;

import java.io.Serializable;

public record UserRequest(String firstName, String lastName, String email, String password) implements Serializable
{
    public UserRequest(String email, String password)
    {
        this(null, null, email, password);
    }
}
