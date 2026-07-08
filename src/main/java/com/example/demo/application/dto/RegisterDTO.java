package com.example.demo.application.dto;

import com.example.demo.domain.enums.Role;

public record RegisterDTO(String name, String email, String password, String cpf, Role role) {
}
