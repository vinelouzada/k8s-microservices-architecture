package vinelouzada.shopping.api;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinelouzada.shopping.api.dto.OrderRequest;
import vinelouzada.shopping.api.dto.OrderResponse;
import vinelouzada.shopping.api.integration.product.ProductClient;
import vinelouzada.shopping.api.integration.product.ProductClientResponse;
import vinelouzada.shopping.api.integration.user.UserClient;
import vinelouzada.shopping.api.integration.user.UserClientResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductClient  productClient;
    private final UserClient userClient;

    public OrderController(OrderRepository orderRepository, ProductClient productClient, UserClient userClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderRepository.findAll().stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> findAllByUserId(Long userId) {
        return ResponseEntity.ok(orderRepository.findByUserId(userId).stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/date/{createdAt}")
    public ResponseEntity<List<OrderResponse>> findAllByCreatedAt(LocalDateTime createdAt) {
        return ResponseEntity.ok(orderRepository.findAllByCreatedAtGreaterThanEqual(createdAt).stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(new OrderResponse(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest orderRequest) {
        UserClientResponse user = userClient.getUserByCpf(orderRequest.cpf()).orElseThrow(EntityNotFoundException::new);
        List<Item> items = new ArrayList<>();
        orderRequest.items().forEach(item -> {
            ProductClientResponse productClientResponse = productClient.getProductById(item.getProductId()).orElseThrow(EntityNotFoundException::new);
            items.add(new Item(productClientResponse.id(), productClientResponse.price()));
        });

        Order order = orderRepository.save(orderRequest.toModel(user, items));

        return ResponseEntity.ok(new OrderResponse(order));
    }
}
