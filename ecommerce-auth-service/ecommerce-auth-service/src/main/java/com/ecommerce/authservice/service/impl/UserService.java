package com.ecommerce.authservice.service.impl;
import com.ecommerce.authservice.dto.request.UserLoginRequest;
import com.ecommerce.authservice.dto.request.UserRegistrationRequest;
import com.ecommerce.authservice.dto.response.UserLoginResponse;
import com.ecommerce.authservice.dto.response.UserResponse;
import com.ecommerce.authservice.entity.User;
import com.ecommerce.authservice.exception.ApplicationException;
import com.ecommerce.authservice.mapper.UserMapper;
import com.ecommerce.authservice.repository.IUserRepository;
import com.ecommerce.authservice.service.IUserService;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final Keycloak keycloak;
    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Override
    public void createUser(UserRegistrationRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        // set password
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(request.getPassword());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = keycloak.realm(realm).users();
        Response response = usersResource.create(user);

        if (response.getStatus() != 201){
            log.error("Error create user", response.getStatusInfo().getReasonPhrase());
            throw new RuntimeException("Failed create user in Keycloak");
        }
        log.info("Created user success",request.getUsername());
    }

    @Override
    public UserLoginResponse login(UserLoginRequest loginRequest) {
        try(Keycloak keycloakClient = Keycloak.getInstance(
                serverUrl, realm, loginRequest.getUsername(), loginRequest.getPassword(), clientId, clientSecret
        )) {
            AccessTokenResponse accessTokenResponse = keycloakClient.tokenManager().getAccessToken();
            return UserLoginResponse.builder()
                    .accessToken(accessTokenResponse.getToken())
                    .refreshToken(accessTokenResponse.getRefreshToken())
                    .build();
        } catch (NotAuthorizedException e){
            throw new RuntimeException("Invalid credential", e);
        }
    }

    @Override
    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ApplicationException("Không tìm thấy User"));
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userMapper.toResponseList(userRepository.findAll());
    }
}
