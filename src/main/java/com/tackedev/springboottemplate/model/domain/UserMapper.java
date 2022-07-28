package com.tackedev.springboottemplate.model.domain;

import com.tackedev.springboottemplate.model.orm.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    default Role map(int role) {
        return Role.fromValue(role);
    }

    User toDomain(UserEntity userEntity);

}
