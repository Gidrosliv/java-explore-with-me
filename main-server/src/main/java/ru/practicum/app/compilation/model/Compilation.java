package ru.practicum.app.compilation.model;


import ru.practicum.app.event.model.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "compilations")
public class Compilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "pinned", nullable = false)
    boolean pinned;
}