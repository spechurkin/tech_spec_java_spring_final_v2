package me.myproj.webrisetest.repos;

import me.myproj.webrisetest.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
  @Query("SELECT s.serviceName, COUNT(us) as count FROM Subscription s JOIN s.userIds us GROUP BY s.serviceName ORDER BY count DESC")
  List<Object[]> findTopSubscriptions();
}