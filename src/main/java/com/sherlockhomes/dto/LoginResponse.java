package com.sherlockhomes.dto;

public record LoginResponse(String token, String tipoUsuario, String username) {
}
