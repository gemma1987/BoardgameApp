package com.boardgameApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.boardgameApp.Domain.Person;

/*
Extends JpaRepository, so uses everything in that repository as well
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
