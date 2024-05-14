package com.junhyeong.heroglass.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
@Data
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private UserRole userRole;
    private String accessToken;
    private String refreshToken;

    @Embedded
    private Address address;


    @JsonIgnore
    @OneToMany(mappedBy = "member") // Order 테이블에 있는 member에 의해 매핑된 거울일 뿐!
    private List<Order> orders = new ArrayList<>();


    public AppUser(String name, String email, String encode, UserRole role) {
        this.username = name;
        this.email = email;
        this.password = encode;
        this.userRole = role;
    }

    public void update(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}

