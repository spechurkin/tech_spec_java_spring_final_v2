package me.myproj.webrisetest.services;

import me.myproj.webrisetest.entities.Subscription;
import me.myproj.webrisetest.entities.dtos.SubscriptionDTO;
import me.myproj.webrisetest.repos.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {
  @Autowired
  private SubscriptionRepository subscriptionRepository;

  public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
    Subscription subscription = new Subscription();
    subscription.setServiceName(subscriptionDTO.getServiceName());
    Subscription savedSubscription = subscriptionRepository.saveAndFlush(subscription);
    return convertToDTO(savedSubscription);
  }

  public List<SubscriptionDTO> getAllSubscriptions() {
    return subscriptionRepository.findAll().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  public void deleteSubscription(Long id) {
    subscriptionRepository.deleteById(id);
  }

  public List<SubscriptionDTO> getTopSubscriptions() {
    List<Object[]> results = subscriptionRepository.findTopSubscriptions();
    return results.stream()
        .map(result -> {
          SubscriptionDTO dto = new SubscriptionDTO();
          dto.setServiceName((String) result[0]);
          return dto;
        })
        .limit(3)
        .sorted(Comparator.comparing(
            SubscriptionDTO::getServiceName)
        ).toList();
  }

  private SubscriptionDTO convertToDTO(Subscription subscription) {
    SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
    subscriptionDTO.setServiceName(subscription.getServiceName());
    return subscriptionDTO;
  }
}