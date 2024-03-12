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

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UsersLoginDTO {
    @NotNull (message = "Email tidak boleh kosong!")
    @NotEmpty (message = "Email tidak boleh kosong!")
    @NotBlank (message = "Email tidak boleh spasi!")
    public String username;
    @NotNull (message = "Password tidak boleh kosong!")
    @NotEmpty (message = "Password tidak boleh kosong!")
    @NotBlank (message = "Password tidak boleh spasi!")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
