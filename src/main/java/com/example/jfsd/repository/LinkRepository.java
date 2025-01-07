package com.example.jfsd.repository;

import com.example.jfsd.model.Link;
import com.example.jfsd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;



public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByUser(User user);
    Optional<Link> findById(Long id);
}
