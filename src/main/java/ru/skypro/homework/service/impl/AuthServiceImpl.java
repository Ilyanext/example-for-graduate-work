package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.projections.Login;
import ru.skypro.homework.projections.Register;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserServiceSecurity;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserServiceSecurity manager;
    private final PasswordEncoder encoder;

    /**
     * Метод авторизации пользователя
     */
    @Override
    public boolean validLogin(String userName, String password) {
        if (manager.loadUserByUsername(userName) == null) {
            return false;
        }
        log.info("Пользователь авторизирован");
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    /**
     * Проверка возможности регистрации пользователя
     */
    @Override
    public boolean validRegister(Register register) {
        return register.getUsername() != null && !register.getUsername().isBlank()
                && register.getFirstName() != null && !register.getFirstName().isBlank()
                && register.getLastName() != null && !register.getLastName().isBlank()
                && register.getPhone() != null && !register.getPhone().isBlank()
                && register.getPassword() != null && !register.getPassword().isBlank();

    }

    /**
     * Регистрация пользователя
     */
    @Override
    public ResponseEntity<?> getRegistration(Register register) {
        if (validRegister(register)) {
            manager.createUser(register);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Авторизация пользователя
     */
    @Override
    public ResponseEntity<?> getLogin(Login login) {
        if (validLogin(login.getUsername(), login.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
