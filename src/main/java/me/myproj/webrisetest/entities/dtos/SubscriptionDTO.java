package me.myproj.webrisetest.entities.dtos;

import lombok.Data;
import me.myproj.webrisetest.entities.Subscription;

import java.io.Serializable;

/**
 * DTO for {@link Subscription}
 */
@Data
public class SubscriptionDTO implements Serializable {
  String serviceName;
}