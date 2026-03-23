package com.johnsavoy.password_manager_api.controller;

import com.johnsavoy.password_manager_api.dto.PasswordEntryRequest;
import com.johnsavoy.password_manager_api.dto.PasswordEntryResponse;
import com.johnsavoy.password_manager_api.service.PasswordEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordEntryController {

    private final PasswordEntryService passwordEntryService;

    public PasswordEntryController(PasswordEntryService passwordEntryService) {
        this.passwordEntryService = passwordEntryService;
    }

    @GetMapping
    public ResponseEntity<List<PasswordEntryResponse>> getAllEntries(Principal principal) {
        return ResponseEntity.ok(
                passwordEntryService.getAllEntries(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PasswordEntryResponse> getEntry(
            @PathVariable Long id, Principal principal) {
        return ResponseEntity.ok(
                passwordEntryService.getEntry(id, principal.getName()));
    }

    @PostMapping
    public ResponseEntity<PasswordEntryResponse> createEntry(
            @RequestBody PasswordEntryRequest request, Principal principal) {
        return ResponseEntity.ok(
                passwordEntryService.createEntry(request, principal.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PasswordEntryResponse> updateEntry(
            @PathVariable Long id,
            @RequestBody PasswordEntryRequest request,
            Principal principal) {
        return ResponseEntity.ok(
                passwordEntryService.updateEntry(id, request, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(
            @PathVariable Long id, Principal principal) {
        passwordEntryService.deleteEntry(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}