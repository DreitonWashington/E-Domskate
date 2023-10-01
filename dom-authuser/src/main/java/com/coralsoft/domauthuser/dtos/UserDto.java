package com.coralsoft.domauthuser.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    public interface UserView{
        public static interface UserUpdate{}
        public static interface RegistrationPost {}
    }

    @JsonView({UserView.UserUpdate.class})
    private UUID userId;
    @JsonView({UserView.UserUpdate.class, UserView.RegistrationPost.class})
    private String fullName;
    @JsonView({UserView.UserUpdate.class, UserView.RegistrationPost.class})
    private String userName;
    @JsonView({UserView.UserUpdate.class, UserView.RegistrationPost.class})
    private String email;
    @JsonView({UserView.RegistrationPost.class})
    private String password;
}
