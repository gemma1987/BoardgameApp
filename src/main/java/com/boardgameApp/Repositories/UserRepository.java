package com.boardgameApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boardgameApp.Domain.User;

/*
Extends JpaRepository, so uses everything in that repository as well
 */
public interface UserRepository extends JpaRepository<User, String> {
}
