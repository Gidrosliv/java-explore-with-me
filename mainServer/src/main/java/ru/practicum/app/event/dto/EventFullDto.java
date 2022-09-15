package ru.practicum.app.event.dto;

import ru.practicum.app.category.CategoryDto;
import ru.practicum.app.user.UserShortDto;


import java.time.LocalDateTime;

public class EventFullDto {


    Integer id;

    String annotation;

    String category;

    CategoryDto confirmedRequests;

    String createdOn;

    String description;

    LocalDateTime eventDate;

    UserShortDto initiator;

    String location; //todo

    boolean paid;

    String participantLimit;

    String publishedOn;

    boolean requestModeration;

    String state;

    String title;

    int views;

}