
package com.devops.authentication.user.service.impl;

import com.devops.authentication.common.constant.RoleConstants;
import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.common.mapper.UserMapper;
import com.devops.authentication.role.entity.Role;
import com.devops.authentication.role.repository.RoleRepository;
import com.devops.authentication.token.service.EmailVerificationService;
import com.devops.authentication.user.dto.request.RegisterRequest;
import com.devops.authentication.user.dto.response.UserResponse;
import com.devops.authentication.user.entity.User;
import com.devops.authentication.user.repository.UserRepository;
import com.devops.authentication.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmailVerificationService emailVerificationService;

    @Override
    public UserResponse registerUser(RegisterRequest request) {

        validateEmail(request.getEmail());

        validatePhoneNumber(request.getPhoneNumber());

        Role defaultRole =
                roleRepository.findByName(RoleConstants.ROLE_USER)
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.ROLE_001
                                )
                        );

        User user =
                userMapper.toEntity(request);

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setEnabled(true);

        user.setAccountNonLocked(true);

        user.setAccountNonExpired(true);

        user.setCredentialsNonExpired(true);

        user.setEmailVerified(false);

        user.setRoles(
                new HashSet<>(
                        Set.of(defaultRole)
                )
        );

        User savedUser =
                userRepository.save(user);

        emailVerificationService.createVerificationToken(
                savedUser
        );

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getCurrentUser(String email) {

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow(() ->
                                new BusinessException(
                                        ErrorCode.USER_001
                                )
                        );

        return userMapper.toResponse(user);
    }

    private void validateEmail(String email) {

        if (userRepository.existsByEmail(email)) {

            throw new BusinessException(
                    ErrorCode.USER_002
            );
        }
    }

    private void validatePhoneNumber(String phoneNumber) {

        if (phoneNumber != null
                && !phoneNumber.isBlank()
                && userRepository.existsByPhoneNumber(phoneNumber)) {

            throw new BusinessException(
                    ErrorCode.USER_003
            );
        }
    }
}
