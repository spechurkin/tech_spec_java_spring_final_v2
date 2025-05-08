package me.myproj.webrisetest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false)
  private String serviceName;

  @ElementCollection
  @CollectionTable(name = "user_subscriptions", joinColumns = @JoinColumn(name = "subscription_id"))
  @Column(name = "user_id")
  private Set<Long> userIds = new HashSet<>();
}