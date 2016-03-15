package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {
    void delete(int id);

    List<UserMealWithExceed> getAll();

    List<UserMealWithExceed> getAllMealsFilteredByDateAndTime(LocalDate startDate, LocalTime startTime,
                                                              LocalDate endDate, LocalTime endTime);
    UserMeal save(UserMeal userMeal);
}
