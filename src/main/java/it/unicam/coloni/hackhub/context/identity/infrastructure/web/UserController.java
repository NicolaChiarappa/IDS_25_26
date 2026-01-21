package it.unicam.coloni.hackhub.context.identity.infrastructure.web;

import it.unicam.coloni.hackhub.context.identity.application.dto.UserDto;
import it.unicam.coloni.hackhub.context.identity.application.dto.request.LoginRequest;
import it.unicam.coloni.hackhub.context.identity.application.dto.request.SignUpRequest;
import it.unicam.coloni.hackhub.context.identity.application.dto.response.LoginResponse;
import it.unicam.coloni.hackhub.context.identity.application.service.AuthService;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponse;
import it.unicam.coloni.hackhub.shared.infrastructure.web.ApiResponseFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final AuthService userService;
    private final ApiResponseFactory factory;


    @Autowired
    public UserController(AuthService service, ApiResponseFactory factory){
        this.userService = service;
        this.factory = factory;

    }
//
//    @GetMapping("/{id}")
//    public ApiResponse<BaseUserDTO> getUserById(@PathVariable(name = "id") Long id){
//        return factory.createSuccessResponse(
//                Messages.Success.ACCOUNT_FOUND,
//                userService.getUserById(id)
//        );
//    }
//
//    @GetMapping("/all")
//    public ApiResponse<List<BaseUserDTO>> getAllUsers(){
//        return factory.createSuccessResponse(
//                Messages.Success.ACCOUNT_FOUND,
//                userService.getAll()
//        );
//
//
//    }
//
//    @GetMapping
//    public ApiResponse<BaseUserDTO> getByUsername(@RequestParam(value = "username" ) String username){
//        return factory.createSuccessResponse(
//                Messages.Success.ACCOUNT_FOUND,
//                userService.getUserByUsername(username)
//        );
//    }




    @PostMapping("/signup")
    public ApiResponse<UserDto> signUp(@RequestBody @Valid SignUpRequest dto){
        System.out.println("signup");
        return factory.createSuccessResponse(
                Messages.Success.ACCOUNT_CREATED,
                userService.signUp(dto)
        );
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request){
        return factory.createSuccessResponse(
                Messages.Success.USER_LOGGED_IN,
                userService.logIn(request)
        );
    }




//    @PatchMapping("/{id}")
//    public ApiResponse<BaseUserDTO> updateUser(@PathVariable(name = "id") Long id, @RequestBody UpdateUserRequest request){
//        return factory.createSuccessResponse(
//                Messages.Success.ACCOUNT_MODIFIED,
//                userService.updateUser(id, request)
//        );
//    }
}
