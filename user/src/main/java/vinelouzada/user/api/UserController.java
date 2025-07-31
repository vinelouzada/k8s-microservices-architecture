package vinelouzada.user.api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinelouzada.user.api.dto.CreateUserRequest;
import vinelouzada.user.api.dto.UserResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest userRequest) {
        User savedUser = userRepository.save(userRequest.toModel());
        return ResponseEntity.ok(new UserResponse(savedUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> getAllUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(userRepository.queryByNameLike(name).stream().map(UserResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/pageable")
    public Page<UserResponse> getUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        return usersPage.map(UserResponse::new);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserResponse> getUserByCpf(@PathVariable String cpf) {
        Optional<User> user = userRepository.findByCpf(cpf);
        return user.map(UserResponse::new).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deleteUser(@PathVariable String cpf) {
        if (!userRepository.existsByCpf(cpf)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteByCpf(cpf);
        return ResponseEntity.noContent().build();
    }
}