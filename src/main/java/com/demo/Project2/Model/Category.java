package com.demo.Project2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "Please provide a ID")
    @Size(min = 3, message = "ID have to be 3 character long")
    private String ID;

    @NotEmpty(message = "Please provide a name")
    @Size(min = 3, message = "have to be 3 length long")
    private String name;
}
