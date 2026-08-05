package com.devops.authentication.token.service.impl;


import com.devops.authentication.common.enums.ErrorCode;
import com.devops.authentication.common.exception.BusinessException;
import com.devops.authentication.token.entity.PasswordResetToken;
import com.devops.authentication.token.repository.PasswordResetTokenRepository;
import com.devops.authentication.token.service.PasswordResetTokenService;
import com.devops.authentication.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class PasswordResetTokenServiceImpl
        implements PasswordResetTokenService {


    private final PasswordResetTokenRepository repository;



    @Override
    public PasswordResetToken createToken(
            User user
    ) {


        repository.deleteByUser(user);


        PasswordResetToken resetToken =
                new PasswordResetToken();


        resetToken.setToken(
                UUID.randomUUID().toString()
        );


        resetToken.setUser(user);


        resetToken.setExpiresAt(
                Instant.now()
                        .plusSeconds(
                                60 * 60
                        )
        );


        resetToken.setUsed(false);


        return repository.save(resetToken);
    }




    @Override
    @Transactional(readOnly = true)
    public PasswordResetToken findByToken(
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
    public boolean isValid(
            PasswordResetToken token
    ) {


        if(token.isUsed()) {

            throw new BusinessException(
                    ErrorCode.TOKEN_002
            );
        }


        if(token.getExpiresAt()
                .isBefore(Instant.now())) {


            throw new BusinessException(
                    ErrorCode.TOKEN_002
            );
        }


        return true;
    }





    @Override
    public void markUsed(
            PasswordResetToken token
    ) {


        token.setUsed(true);


        repository.save(token);

    }





    @Override
    public void deleteByUser(
            User user
    ) {


        repository.deleteByUser(user);

    }

}