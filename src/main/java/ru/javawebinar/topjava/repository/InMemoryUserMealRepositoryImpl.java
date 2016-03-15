package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public boolean save(UserMeal userMeal) {
        for (Map.Entry<Integer, Map<Integer, UserMeal>> pair : repository.entrySet()){
            if (userMeal.isNew()) {
                userMeal.setId(counter.incrementAndGet());
                pair.getValue().put(userMeal.getId(), userMeal);
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        for (Map.Entry<Integer, Map<Integer, UserMeal>> pair : repository.entrySet()){
            if (pair.getValue().containsKey(id)){
                pair.getValue().remove(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public UserMeal get(int userId, int mealId) {
        for (Map.Entry<Integer, Map<Integer, UserMeal>> pair : repository.entrySet()){
            if (pair.getKey() == userId)
                return pair.getValue().get(mealId);
        }
        return null;
    }

    @Override
    public Collection<UserMeal> getAllUsersMeals(int userId) {
        List<UserMeal> userMealsSortedByTime = new ArrayList<>();
        for (Map.Entry<Integer, Map<Integer, UserMeal>> pair : repository.entrySet()) {
            if (pair.getKey() == userId) {
                userMealsSortedByTime.addAll(pair.getValue().values());
            }
        }

        Collections.sort(userMealsSortedByTime, (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
        return userMealsSortedByTime;
    }

    @Override
    public Collection<UserMeal> getAll() {
        List<UserMeal> userMealsSortedByTime = new ArrayList<>();
        for (Map.Entry<Integer, Map<Integer, UserMeal>> pair : repository.entrySet()) {
            userMealsSortedByTime.addAll(pair.getValue().values());
        }

        Collections.sort(userMealsSortedByTime, (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
        return userMealsSortedByTime;
    }
}

