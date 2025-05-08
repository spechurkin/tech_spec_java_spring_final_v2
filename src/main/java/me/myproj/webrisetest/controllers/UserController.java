package me.myproj.webrisetest.controllers;

import me.myproj.webrisetest.entities.dtos.UserDTO;
import me.myproj.webrisetest.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @PostMapping
  public UserDTO createUser(@RequestBody UserDTO userDTO) {
    logger.info("User {} created", userDTO.getName());
    return userService.createUser(userDTO);
  }

  @GetMapping
  public List<UserDTO> getAllUsers() {
    logger.info("All users requested");
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  public UserDTO getUserById(@PathVariable Long id) {
    logger.info("User {} was requested", id);
    return userService.getUserById(id).orElse(null);
  }

  @PutMapping("/{id}")
  public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
    logger.info("User {} was updated", id);
    return userService.updateUser(id, userDTO);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    logger.warn("User {} was deleted", id);
    userService.deleteUser(id);
  }

  @PostMapping("/{userId}/subscriptions/{subscriptionId}")
  public UserDTO addSubscriptionToUser(@PathVariable Long userId, @PathVariable Long subscriptionId) {
    logger.info("Subscriptions from {} was requested", userId);
    return userService.addSubscriptionToUser(userId, subscriptionId);
  }

  @DeleteMapping("/{userId}/subscriptions/{subscriptionId}")
  public UserDTO removeSubscriptionFromUser(@PathVariable Long userId, @PathVariable Long subscriptionId) {
    logger.warn("Subscription {} was deleted from {}", subscriptionId, userId);
    return userService.removeSubscriptionFromUser(userId, subscriptionId);
  }
}