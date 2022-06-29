package com.example.my_shop.entity.model;

import com.example.my_shop.entity.enam.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table


public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private Integer id;
    @NotBlank(message = "the name cannot be empty")
    @Column
    private String username;
    @NotBlank(message = "the password cannot be empty" )
    @Column
    private String password;
    @NotBlank(message = "the email cannot be empty")
    @Email(message = "wrong email")
    @Column( unique = true, nullable = false)
    private String email;
    @NotBlank(message = "the address cannot be empty")
    @Column
    private String address;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private List<Role> roles;
    @Column
    private boolean active = true;
    @NotBlank(message = "the payment cannot be empty")
    @Column
    private String payment;

    @ManyToMany
   @JoinTable(name = "user_product",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> productList;//
    @Column
    private String activationCode;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "user")
    private List<Order> orders;

    //security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.toString())));
        return authorities;
    }
    @Override
    public String getUsername () {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    //Method for verifying a user with administrator rights.
    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }




}
