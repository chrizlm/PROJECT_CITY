package com.chris.cityparking.repositories;

import com.chris.cityparking.modules.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);

    AppUser getByEmail(String email);

    Optional<AppUser> findByFirstName(String firstName);

    Boolean existsByFirstName(String firstName);

    Boolean existsByEmail(String email);

    void deleteByEmail(String email);
}

/**
 * repo for app user
 */