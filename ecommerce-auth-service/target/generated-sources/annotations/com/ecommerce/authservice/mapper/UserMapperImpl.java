package com.ecommerce.authservice.mapper;

import com.ecommerce.authservice.dto.response.UserResponse;
import com.ecommerce.authservice.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-07T17:11:05+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( user.getId() );
        userResponse.setUsername( user.getUsername() );
        userResponse.setEmail( user.getEmail() );
        userResponse.setPassword( user.getPassword() );
        userResponse.setRole( user.getRole() );

        return userResponse;
    }

    @Override
    public List<UserResponse> toResponseList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( userList.size() );
        for ( User user : userList ) {
            list.add( toResponse( user ) );
        }

        return list;
    }
}
