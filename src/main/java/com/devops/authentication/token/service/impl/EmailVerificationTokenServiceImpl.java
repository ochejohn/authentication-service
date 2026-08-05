package com.devops.authentication.token.service.impl;

import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.token.entity.EmailVerificationToken;
import com.devops.authentication.token.repository.EmailVerificationTokenRepository;
import com.devops.authentication.token.service.EmailVerificationTokenService;
import com.devops.authentication.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailVerificationTokenServiceImpl
        implements EmailVerificationTokenService {

    private final EmailVerificationTokenRepository repository;

    @Override
    public EmailVerificationToken save(
            EmailVerificationToken token
    ) {

        return repository.save(token);
    }

    @Override
    @Transactional(readOnly = true)
    public EmailVerificationToken findByToken(
            String token
    ) {

        return repository.findByToken(token)
                .orElseThrow(() ->
                        new BusinessException(
                                ErrorCode.TOKEN_001
                        )
                );
    }

    @Override
    public void delete(
            EmailVerificationToken token
    ) {

        repository.delete(token);
    }

    @Override
    public void deleteByUser(
            User user
    ) {

        repository.deleteByUser(user);
    }
}