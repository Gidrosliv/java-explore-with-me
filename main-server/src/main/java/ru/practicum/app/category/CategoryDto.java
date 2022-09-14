package ru.practicum.app.category;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;

    @NotEmpty(message = "не может быть пустым")
    @Size(min = 1, max = 250, message = "должно быть менее 250 символов")
    private String name;
}
