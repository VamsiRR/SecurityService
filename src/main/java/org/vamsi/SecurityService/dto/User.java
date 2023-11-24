package org.vamsi.SecurityService.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import static jakarta.persistence.GenerationType.TABLE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details", schema = "security")
public class User {

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Id
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;

    @Column(name = "role")
    String role;
}
