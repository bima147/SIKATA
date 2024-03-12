package coid.bcafinance.bpsringbootfinal.dto.user.authentication;

/*
IntelliJ IDEA 2023.3.4 (Ultimate Edition)
Build #IU-233.14475.28, built on February 13, 2024
@Author Acer-01 a.k.a. Bima Prakoso
Java Developer
Created on 02/03/2024 19:40
@Last Modified 02/03/2024 19:40
Version 1.0
*/

import coid.bcafinance.bpsringbootfinal.model.Access;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.util.Date;

@Data
public class UsersRegistrationDTO {
    private Long access;
    @NotNull (message = "Nama tidak boleh kosong")
    @NotEmpty (message = "Nama tidak boleh kosong")
    @NotBlank (message = "Nama tidak boleh blank")
    public String name;
    @NotNull (message = "Jenis kelamin tidak boleh kosong")
    @NotEmpty (message = "Jenis kelamin tidak boleh kosong")
    @NotBlank (message = "Jenis kelamin tidak boleh blank")
    public String gender;
    @NotNull (message = "Username tidak boleh kosong")
    @NotEmpty (message = "Username tidak boleh kosong")
    @NotBlank (message = "Username tidak boleh blank")
    private String username;
    @NotNull (message = "No Hp tidak boleh kosong")
    @NotEmpty (message = "No Hp tidak boleh kosong")
    @NotBlank (message = "No Hp tidak boleh blank")
    public String phone;
    @NotNull (message = "Email tidak boleh kosong")
    @NotEmpty (message = "Email tidak boleh kosong")
    @NotBlank (message = "Email tidak boleh blank")
    public String email;
    @NotNull (message = "Password tidak boleh kosong")
    @NotEmpty (message = "Password tidak boleh kosong")
    @NotBlank (message = "Password tidak boleh blank")
    @Pattern(regexp = "^([a-zA-Z0-9.,!@]){8,}$", message = "Harus mengandung huruf besar, huruf kecil, simbol (.,!@) " +
            "dan minimal 8 karakter!")
    public String password;

    public Long getAccess() {
        return access;
    }

    public void setAccess(Long access) {
        this.access = access;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
