package com.arnaud.api.repository;

import com.arnaud.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByEmail(String email);
}
