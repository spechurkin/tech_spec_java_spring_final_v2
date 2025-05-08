package me.myproj.webrisetest.repos;

import me.myproj.webrisetest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}