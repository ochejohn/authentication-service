package com.devops.authentication.common.mapper;

import com.devops.authentication.user.dto.request.RegisterRequest;
import com.devops.authentication.user.dto.response.UserResponse;
import com.devops.authentication.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "emailVerified", constant = "false")
    @Mapping(target = "roles", ignore = true)
    User toEntity(RegisterRequest request);

    UserResponse toResponse(User user);

}