package vinelouzada.user.api.dto;

import vinelouzada.user.api.User;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String cpf,
        String address,
        String email,
        String phone,
        LocalDateTime createdAt
) {
    public UserResponse(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getAddress(), user.getEmail(), user.getPhone(), user.getCreatedAt());
    }
}
