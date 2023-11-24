package org.vamsi.SecurityService.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_credentials", schema = "auth")
public class UserCredentials {

    @Id
    @Column(name = "user_id")
    Integer userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "password")
    String password;

    public UserCredentials withId(Integer id)
    {
        this.userId = id;
        return this;
    }
}
