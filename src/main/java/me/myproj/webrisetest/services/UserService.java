package me.myproj.webrisetest.services;

import me.myproj.webrisetest.entities.User;
import me.myproj.webrisetest.entities.dtos.UserDTO;
import me.myproj.webrisetest.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public UserDTO createUser(UserDTO userDTO) {
    User user = new User();
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    User savedUser = userRepository.saveAndFlush(user);
    return convertToDTO(savedUser);
  }

  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream().map(this::convertToDTO).toList();
  }

  public Optional<UserDTO> getUserById(Long id) {
    return userRepository.findById(id).map(this::convertToDTO);
  }

  public UserDTO updateUser(Long id, UserDTO userDTO) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    User updatedUser = userRepository.saveAndFlush(user);
    return convertToDTO(updatedUser);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  public UserDTO addSubscriptionToUser(Long userId, Long subscriptionId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    user.getSubscriptionIds().add(subscriptionId);
    User updatedUser = userRepository.saveAndFlush(user);
    return convertToDTO(updatedUser);
  }

  public UserDTO removeSubscriptionFromUser(Long userId, Long subscriptionId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    user.getSubscriptionIds().remove(subscriptionId);
    User updatedUser = userRepository.saveAndFlush(user);
    return convertToDTO(updatedUser);
  }

  private UserDTO convertToDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setName(user.getName());
    userDTO.setEmail(user.getEmail());
    return userDTO;
  }
}