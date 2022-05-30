package com.demo.Project2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Comment {
    @NotEmpty(message = "id is required")
    @Size(min = 3, message = "ID have to be 3 character long")
    private String id;

    @NotEmpty(message = "userID is required")
    @Size(min = 3, message = "userID have to be 5 length long")
    private String userID;

    @NotEmpty(message = "message is required")
    @Size(min = 3, message = "message have to be 6 length long")
    private String message;

    @NotNull(message = "rate is required")
    @Size(min = 3, max = 5, message = "rate must be a number between 1 - 5")
    private Integer rate;

}
