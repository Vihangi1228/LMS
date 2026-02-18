package com.gl.lms.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class AuthorsDTO {
    private Integer id;

    @NotNull(message = "Name should not be null")
    @NotBlank(message = "Name should not be blank")
    private String name;

    @NotEmpty(message = "Books should not be null or empty")
    @Valid
    private List<BooksDTO> booksDTOS;

}

