package com.icanlearn.conferencedemo.repositories;

import com.icanlearn.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SessionRepository extends JpaRepository<Session, Long> {
}
