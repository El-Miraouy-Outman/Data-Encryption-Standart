package com.miraouy.dataencrptionstandart;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

}
