package com.codewithmosh.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
   // @JsonIgnore Use this if there is other code that depends on this code
   // @JsonProperty("user_id") changes the name of the table to what is passed in the parameter
    public Long id;
    public String name;
    public String email;
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Formats the time
    // private LocalDateTime createAt; Created a variable to display in the UserMapper class
    // @JsonInclude(JsonInclude.Include.NON_NULL) if the data is null the table will be ignored and not displayed
     // private String phoneNumber; The users do not have a phone number is this api
}
