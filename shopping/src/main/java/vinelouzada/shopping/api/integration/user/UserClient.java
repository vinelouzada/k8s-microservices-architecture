package vinelouzada.shopping.api.integration.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserClient {

    @Value("${USER_API_URL:http://localhost:8080}")
    private String userApiURL;

    public Optional<UserClientResponse> getUserByCpf(String cpf) {
        WebClient webClient = WebClient.builder()
                .baseUrl(userApiURL)
                .build();

        Mono<UserClientResponse> user = webClient.get()
                .uri("/user/cpf/" + cpf)
                .retrieve()
                .bodyToMono(UserClientResponse.class);

        return Optional.ofNullable(user.block());
    }
}
