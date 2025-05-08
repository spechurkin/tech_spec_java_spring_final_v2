package me.myproj.webrisetest.controllers;

import me.myproj.webrisetest.entities.dtos.SubscriptionDTO;
import me.myproj.webrisetest.services.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
  private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

  @Autowired
  private SubscriptionService subscriptionService;

  @PostMapping
  public SubscriptionDTO createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
    logger.info("Subscription {} was created", subscriptionDTO.getServiceName());
    return subscriptionService.createSubscription(subscriptionDTO);
  }

  @GetMapping
  public List<SubscriptionDTO> getAllSubscriptions() {
    logger.info("All subscriptions requested");
    return subscriptionService.getAllSubscriptions();
  }

  @DeleteMapping("/{id}")
  public void deleteSubscription(@PathVariable Long id) {
    logger.warn("Subscription {} was deleted", id);
    subscriptionService.deleteSubscription(id);
  }

  @GetMapping("/top")
  public List<SubscriptionDTO> getTopSubscriptions() {
    logger.info("Top-3 subscriptions!");
    return subscriptionService.getTopSubscriptions();
  }
}