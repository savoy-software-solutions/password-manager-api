package com.johnsavoy.password_manager_api.service;

import com.johnsavoy.password_manager_api.dto.PasswordEntryRequest;
import com.johnsavoy.password_manager_api.dto.PasswordEntryResponse;
import com.johnsavoy.password_manager_api.model.PasswordEntry;
import com.johnsavoy.password_manager_api.model.User;
import com.johnsavoy.password_manager_api.repository.PasswordEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasswordEntryService {

    private final PasswordEntryRepository passwordEntryRepository;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public PasswordEntryService(PasswordEntryRepository passwordEntryRepository,
                                UserService userService,
                                EncryptionService encryptionService) {
        this.passwordEntryRepository = passwordEntryRepository;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    public List<PasswordEntryResponse> getAllEntries(String username) {
        User user = userService.findByUsername(username);
        return passwordEntryRepository.findByUserId(user.getId())
                .stream()
                .map(entry -> new PasswordEntryResponse(
                        entry.getId(),
                        entry.getSiteName(),
                        entry.getSiteUrl(),
                        entry.getSiteUsername(),
                        encryptionService.decrypt(entry.getEncryptedPassword())
                ))
                .collect(Collectors.toList());
    }

    public PasswordEntryResponse getEntry(Long id, String username) {
        User user = userService.findByUsername(username);
        PasswordEntry entry = passwordEntryRepository
                .findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        return new PasswordEntryResponse(
                entry.getId(),
                entry.getSiteName(),
                entry.getSiteUrl(),
                entry.getSiteUsername(),
                encryptionService.decrypt(entry.getEncryptedPassword())
        );
    }

    public PasswordEntryResponse createEntry(PasswordEntryRequest request, String username) {
        User user = userService.findByUsername(username);

        PasswordEntry entry = new PasswordEntry();
        entry.setUser(user);
        entry.setSiteName(request.getSiteName());
        entry.setSiteUrl(request.getSiteUrl());
        entry.setSiteUsername(request.getSiteUsername());
        entry.setEncryptedPassword(
                encryptionService.encrypt(request.getPassword()));

        passwordEntryRepository.save(entry);

        return new PasswordEntryResponse(
                entry.getId(),
                entry.getSiteName(),
                entry.getSiteUrl(),
                entry.getSiteUsername(),
                request.getPassword()
        );
    }

    public PasswordEntryResponse updateEntry(Long id,
                                             PasswordEntryRequest request,
                                             String username) {
        User user = userService.findByUsername(username);
        PasswordEntry entry = passwordEntryRepository
                .findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        entry.setSiteName(request.getSiteName());
        entry.setSiteUrl(request.getSiteUrl());
        entry.setSiteUsername(request.getSiteUsername());
        entry.setEncryptedPassword(
                encryptionService.encrypt(request.getPassword()));
        entry.setUpdatedAt(LocalDateTime.now());

        passwordEntryRepository.save(entry);

        return new PasswordEntryResponse(
                entry.getId(),
                entry.getSiteName(),
                entry.getSiteUrl(),
                entry.getSiteUsername(),
                request.getPassword()
        );
    }

    public void deleteEntry(Long id, String username) {
        User user = userService.findByUsername(username);
        PasswordEntry entry = passwordEntryRepository
                .findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Entry not found"));
        passwordEntryRepository.delete(entry);
    }
}