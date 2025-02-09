package com.project.demo.models;

import com.project.demo.models.Enumerations.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor

@Data
@Builder
@Entity
@ToString
@Table(name = "user_account")
public class UserAccount implements UserDetails {
    @Id
    @SequenceGenerator(

            allocationSize = 1,
            name = "user_account_id_sequence",
            sequenceName = "user_account_id_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_account_id_sequence"
    )

    @Column(name = "id")
    private Long id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(length = 1000)
    String  image;

    @Column(nullable = false)
    String phone;


    boolean isVerified=true;
    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=true;
    private boolean available;
    private boolean canUseCommunityChat=true;

    private LocalDateTime createdAt = LocalDateTime.now();


    private LocalDateTime updatedAt =LocalDateTime.now();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
