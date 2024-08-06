package com.geplatform.geviews.data;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;


@Data
@Document(collection = "application_user")
public class User{

    @Id
    private String id;

    private String username;
    private String name;
    @JsonIgnore
    private String hashedPassword;

    private Set<Role> roles;

    private byte[] profilePicture;

}
