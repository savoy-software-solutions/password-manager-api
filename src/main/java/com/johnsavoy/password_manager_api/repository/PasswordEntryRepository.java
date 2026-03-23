package com.johnsavoy.password_manager_api.repository;

import com.johnsavoy.password_manager_api.model.PasswordEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordEntryRepository extends JpaRepository<PasswordEntry, Integer> {
    List<PasswordEntry> findByUserId(Long userId);
    Optional<PasswordEntry> findByIdAndUserId(Long id, Long userId);
}
