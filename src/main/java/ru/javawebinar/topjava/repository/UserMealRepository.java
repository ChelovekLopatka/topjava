package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    boolean save(UserMeal userMeal);

    boolean delete(int id);

    UserMeal get(int userId, int mealId);

    Collection<UserMeal> getAllUsersMeals(int userId);

    Collection<UserMeal> getAll();
}
