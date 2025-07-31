package vinelouzada.user.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import vinelouzada.user.api.User;

import java.time.LocalDateTime;

public record CreateUserRequest(
        @NotBlank
        String name,
        @NotBlank
        String cpf,
        String address,
        @NotBlank
        @Email
        String email,
        String phone
) {
        public User toModel() {
                return new User(name(), cpf(), address(), email(), phone(), LocalDateTime.now());
        }
}
