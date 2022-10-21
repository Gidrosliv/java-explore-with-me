package ru.practicum.app.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentOutDto {

    private Integer id;

    private String text;

    private Integer eventId;

}
