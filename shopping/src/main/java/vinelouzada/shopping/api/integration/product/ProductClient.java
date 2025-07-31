package vinelouzada.shopping.api.integration.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ProductClient {

    @Value("${PRODUCT_API_URL:http://localhost:8081}")
    private String productApiURL;

    public Optional<ProductClientResponse> getProductById(Long id) {
        WebClient webClient = WebClient.builder()
                .baseUrl(productApiURL)
                .build();

        Mono<ProductClientResponse> product = webClient.get()
                .uri("/product/" + id)
                .retrieve()
                .bodyToMono(ProductClientResponse.class);

        return Optional.ofNullable(product.block());
    }
}
