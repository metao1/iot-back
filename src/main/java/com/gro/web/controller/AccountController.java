package com.gro.web.controller;

import com.gro.exception.AccountResourceException;
import com.gro.exception.EmailAlreadyUsedException;
import com.gro.exception.InvalidPasswordException;
import com.gro.model.ManagedUserDTO;
import com.gro.model.User;
import com.gro.model.UserDTO;
import com.gro.repository.UserRepository;
import com.gro.utils.SecurityUtils;
import com.gro.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountController {

  private final Logger log = LoggerFactory.getLogger(AccountController.class);

  private final UserRepository userRepository;

  private final UserService userService;

  public AccountController(UserRepository userRepository, UserService userService) {

    this.userRepository = userRepository;
    this.userService = userService;
  }

 /* *//**
   * {@code POST  /register} : register the user.
   *
   * @param managedUserDTO the managed user View Model.
   * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   *//*
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerAccount(@Valid @RequestBody ManagedUserDTO managedUserDTO) {
    if (!checkPasswordLength(managedUserDTO.getPassword())) {
      throw new InvalidPasswordException();
    }
   // User user = userService.registerUser(managedUserDTO, managedUserDTO.getPassword());
    //mailService.sendActivationEmail(user);
  }*/

  /**
   * {@code GET  /activate} : activate the registered user.
   *
   * @param key the activation key.
   * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be activated.
   */
  @GetMapping("/activate")
  public void activateAccount(@RequestParam(value = "key") String key) {
    Optional<User> user = userService.activateRegistration(key);
    if (!user.isPresent()) {
      throw new AccountResourceException("No user was found for this activation key");
    }
  }

  /**
   * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
   *
   * @param request the HTTP request.
   * @return the login if the user is authenticated.
   */
  @GetMapping("/authenticate")
  public String isAuthenticated(HttpServletRequest request) {
    log.debug("REST request to check if the current user is authenticated");
    return request.getRemoteUser();
  }

  /**
   * {@code GET  /account} : get the current user.
   *
   * @return the current user.
   * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
   */
  @GetMapping("/account")
  public UserDTO getAccount() {
    return userService.getUserWithAuthorities()
      .map(UserDTO::new)
      .orElseThrow(() -> new AccountResourceException("User could not be found"));
  }

  /**
   * {@code POST  /account} : update the current user information.
   *
   * @param userDTO the current user information.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws RuntimeException          {@code 500 (Internal Server Error)} if the user login wasn't found.
   */
  @PostMapping("/account")
  public void saveAccount(@Valid @RequestBody UserDTO userDTO) {
    String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() ->
      new AccountResourceException("Current user login not found"));
    Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
    if (existingUser.isPresent() && (!existingUser.get().getEmail().equalsIgnoreCase(userLogin))) {
      throw new EmailAlreadyUsedException();
    }
    Optional<User> user = userRepository.findOneByEmail(userLogin);
    if (!user.isPresent()) {
      throw new AccountResourceException("User could not be found");
    }
    userService.updateUser(userDTO.getName(), userDTO.getEmail(), userDTO.getLangKey(), userDTO.getImageUrl());
  }

  /**
   * {@code POST  /account/change-password} : changes the current user's password.
   *
   * @param passwordChangeDto current and new password.
   * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new password is incorrect.
   *//*
  @PostMapping(path = "/account/change-password")
  public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
    if (!checkPasswordLength(passwordChangeDto.getNewPassword())) {
      throw new InvalidPasswordException();
    }
    userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
  }

  *//**
   * {@code POST   /account/reset-password/init} : Send an email to reset the password of the user.
   *
   * @param mail the mail of the user.
   * @throws EmailNotFoundException {@code 400 (Bad Request)} if the email address is not registered.
   *//*
  @PostMapping(path = "/account/reset-password/init")
  public void requestPasswordReset(@RequestBody String mail) {
    mailService.sendPasswordResetMail(
      userService.requestPasswordReset(mail)
        .orElseThrow(EmailNotFoundException::new)
    );
  }

  *//**
   * {@code POST   /account/reset-password/finish} : Finish to reset the password of the user.
   *
   * @param keyAndPassword the generated key and the new password.
   * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
   * @throws RuntimeException         {@code 500 (Internal Server Error)} if the password could not be reset.
   *//*
  @PostMapping(path = "/account/reset-password/finish")
  public void finishPasswordReset(@RequestBody KeyAndPasswordDTO keyAndPassword) {
    if (!checkPasswordLength(keyAndPassword.getNewPassword())) {
      throw new InvalidPasswordException();
    }
    Optional<User> user =
      userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

    if (!user.isPresent()) {
      throw new AccountResourceException("No user was found for this reset key");
    }
  }*/

  private static boolean checkPasswordLength(String password) {
    return !StringUtils.isEmpty(password) &&
      password.length() >= ManagedUserDTO.PASSWORD_MIN_LENGTH &&
      password.length() <= ManagedUserDTO.PASSWORD_MAX_LENGTH;
  }
}
