package me.myproj.webrisetest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false)
  private String name;
  @Column(unique = true, nullable = false)
  private String email;

  @ElementCollection
  @CollectionTable(name = "user_subscriptions", joinColumns = @JoinColumn(name = "user_id"))
  @Column(name = "subscription_id")
  private Set<Long> subscriptionIds = new HashSet<>();
}