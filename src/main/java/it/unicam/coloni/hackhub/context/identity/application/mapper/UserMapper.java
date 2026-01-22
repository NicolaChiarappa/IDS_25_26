package it.unicam.coloni.hackhub.context.identity.application.mapper;

import it.unicam.coloni.hackhub.context.identity.application.dto.UserDto;
import it.unicam.coloni.hackhub.context.identity.application.dto.request.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.dto.request.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.utility.PasswordHelper;
import it.unicam.coloni.hackhub.context.identity.domain.model.User;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.time.LocalDateTime;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses={PasswordHelper.class, UserMapper.class}
)
public abstract class UserMapper {




    public abstract UserDto toDto(User user);


    @Mapping(target = "createdAt", source=".",  qualifiedByName = "getCurrentTime")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    public abstract User fromSignUp(@NotNull SignUpRequest request);


    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "email", ignore = true)
    public abstract User fromLogin(LoginRequest request);



    @Named("getCurrentTime")
    protected LocalDateTime getCurrentTime(SignUpRequest dto) {
        return LocalDateTime.now();
    }
}
