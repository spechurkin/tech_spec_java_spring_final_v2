package me.myproj.webrisetest.entities.dtos;

import lombok.Data;
import me.myproj.webrisetest.entities.User;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Data
public class UserDTO implements Serializable {
  String name;
  String email;
}