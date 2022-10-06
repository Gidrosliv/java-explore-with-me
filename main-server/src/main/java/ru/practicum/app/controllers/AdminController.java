package ru.practicum.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.practicum.app.category.CategoryDto;
import ru.practicum.app.category.CategoryServiceImpl;
import ru.practicum.app.category.NewCategoryDto;
import ru.practicum.app.compilation.CompilationDto;
import ru.practicum.app.compilation.CompilationServiceImpl;
import ru.practicum.app.compilation.NewCompilationDto;
import ru.practicum.app.event.*;
import ru.practicum.app.exception.EntryNotFoundException;
import ru.practicum.app.user.UserDto;
import ru.practicum.app.user.UserServiceImpl;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class AdminController {

    private final UserServiceImpl userService;

    private final EventServiceImpl eventService;

    private final CompilationServiceImpl compilationService;

    private final CategoryServiceImpl categoryService;

    @Autowired
    public AdminController(CategoryServiceImpl categoryService,
                           CompilationServiceImpl compilationService,
                           EventServiceImpl eventService,
                           UserServiceImpl userService) {
        this.categoryService = categoryService;
        this.compilationService = compilationService;
        this.eventService = eventService;
        this.userService = userService;
    }


    /***
     * Работа с категория от имени Администратора
     * - cоздание новой категории
     * - обновление категории
     * - удаление категорий
     ***/

    @PostMapping(value = "/admin/categories")
    public CategoryDto create(@RequestBody NewCategoryDto newCategoryDto) {
        return categoryService.create(newCategoryDto);
    }

    @PatchMapping(value = "/admin/categories")
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }

    @DeleteMapping(value = "/admin/categories/{catId}")
    public void delete(@PathVariable(value = "catId") @NotNull int catId) {
        categoryService.delete(catId);
    }

    /***
     * Работа с Подборками от имени Администратора
     * - cоздание новой подборки
     * - удаление подборки
     * - добавление события в подборку
     * - удаление события из подборки
     * - закрепить подборку
     * - открепить подборку
     ***/


    @PostMapping("/admin/compilations")
    public CompilationDto addCompilation(@RequestBody NewCompilationDto newCompilationDto) {
        return compilationService.addCompilation(newCompilationDto);
    }

    @DeleteMapping("/admin/compilations/{compId}")
    public void deleteCompilationById(@PathVariable Integer compId) {
        compilationService.deleteCompilationById(compId);
    }

    @PatchMapping("/admin/compilations/{compId}/events/{eventId}")
    public void addEventToCompilation(@PathVariable Integer compId,
                                      @PathVariable Integer eventId) {
        compilationService.addEventToCompilation(compId, eventId);
    }

    @DeleteMapping("/admin/compilations/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable Integer compId,
                                           @PathVariable Integer eventId) {
        compilationService.deleteEventFromCompilation(compId, eventId);
    }

    @PatchMapping("/admin/compilations/{compId}/pin")
    public void pinCompilation(@PathVariable Integer compId) {
        compilationService.pinCompilation(compId);
    }

    @DeleteMapping("/admin/compilations/{compId}/pin")
    public void unpinCompilation(@PathVariable Integer compId) {
        compilationService.unpinCompilation(compId);
    }

    /*** Admin: События. API для работы с событиями
     * - поиск событий
     * - редактирование события
     * - публикация события
     * - отклонить событие
     */

    @PutMapping(value = "/admin/events/{eventId}")
    public EventFullDto updateEvent(@PathVariable int eventId,
                                    @RequestBody AdminUpdateEventRequest adminUpdateEventRequest) {
        return eventService.updateEvent(eventId, adminUpdateEventRequest);
    }

    @PatchMapping(value = "/admin/events/{eventId}/publish")
    public EventFullDto publishEvent(@PathVariable int eventId) {
        return eventService.publishEvent(eventId);
    }

    @PatchMapping(value = "/admin/events/{eventId}/reject")
    public EventFullDto rejectEvent(@PathVariable int eventId) {
        return eventService.rejectEvent(eventId);
    }

//    @PatchMapping(value = "/admin/events/{eventId}/reject")
//    public Event rejectEvent(@PathVariable int eventId) {
//        return eventService.rejectEvent(eventId);
//    }

    @GetMapping(value = "/admin/events")
    public Collection<EventFullDto> getEventByAdmin(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<String> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) String rangeStart,
            @RequestParam(required = false) String rangeEnd,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size) {
        return eventService.getEventByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    /***
     *  Admin: Пользователи. API для работы с пользователями
     *  - добавление нового польщователя
     *  - получение списка пользователей
     *  - удаление пользователя
     */

    @PostMapping(value = "/admin/users")
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }


    @GetMapping(value = "/admin/users")
    public List<UserDto> getUser(@RequestParam(required = false) List<Integer> ids,
                                 @RequestParam(defaultValue = "0") Integer from,
                                 @RequestParam(defaultValue = "10") Integer size) {
//        if (ids != null && !ids.isEmpty()) return userService.getUsersById(ids);
        return userService.getAllUsers(ids,from, size);
    }

    @DeleteMapping(value = "admin/users/{userId}")
    public void deleteUser(@PathVariable @NotNull int userId) throws EntryNotFoundException {
        userService.delete(userId);
    }

//    @GetMapping
//    public List<EventShortDto> getEvents(@RequestParam(required = false) String text,
//                                         @RequestParam(required = false) int[] categories,
//                                         @RequestParam(required = false) Boolean isPaid,
//                                         @RequestParam(required = false) String rangeStart,
//                                         @RequestParam(required = false) String rangeEnd,
//                                         @RequestParam(defaultValue = "false") boolean onlyAvailable,
//                                         @RequestParam(required = false) EventSortValues sort,
//                                         @PositiveOrZero @RequestParam(defaultValue = "0") int from,
//                                         @Positive @RequestParam(defaultValue = "10") int size,
//                                         HttpServletRequest request) {
//        return eventService.getEvents(request, text, categories, isPaid, rangeStart, rangeEnd, onlyAvailable,
//                sort.name(), from, size);
//    }

}