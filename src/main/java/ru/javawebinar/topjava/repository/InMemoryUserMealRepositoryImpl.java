package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id) {
        if (repository.containsKey(id)){
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public UserMeal get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        List<UserMeal> userMealsSortedByTime = new ArrayList<>();
        userMealsSortedByTime.addAll(repository.values());

        Collections.sort(userMealsSortedByTime, (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
        return userMealsSortedByTime;
    }

    //    @Override
//    public Collection<UserMeal> getAllUsersMeals(int userId) {
//        List<UserMeal> userMealsSortedByTime = new ArrayList<>();
//        for (Map.Entry<Integer, Map<Integer, UserMeal>> pair : repository.entrySet()) {
//            if (pair.getKey() == userId) {
//                userMealsSortedByTime.addAll(pair.getValue().values());
//            }
//        }
//
//        Collections.sort(userMealsSortedByTime, (o1, o2) -> o1.getDateTime().compareTo(o2.getDateTime()));
//        return userMealsSortedByTime;
//    }
}

