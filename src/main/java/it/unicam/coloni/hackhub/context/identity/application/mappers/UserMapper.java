package it.unicam.coloni.hackhub.context.identity.application.mappers;

import it.unicam.coloni.hackhub.context.identity.application.dtos.BaseUserDTO;
import it.unicam.coloni.hackhub.context.identity.application.requests.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.requests.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.utilities.PasswordHelper;
import it.unicam.coloni.hackhub.context.identity.domain.models.User;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses={PasswordHelper.class}
)
public interface UserMapper {




    BaseUserDTO toDto(User user);


    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    User fromSignUp(@NotNull SignUpRequest request);


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "email", ignore = true)

    User fromLogin(LoginRequest request);


}
