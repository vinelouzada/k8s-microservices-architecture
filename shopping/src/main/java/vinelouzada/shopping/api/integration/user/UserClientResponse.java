package vinelouzada.shopping.api.integration.user;

import java.time.LocalDateTime;

public record UserClientResponse(
        Long id,
        String name,
        String cpf,
        String address,
        String email,
        String phone,
        LocalDateTime createdAt
) {
}