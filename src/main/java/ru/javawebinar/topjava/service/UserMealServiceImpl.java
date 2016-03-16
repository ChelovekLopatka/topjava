package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public List<UserMealWithExceed> getAll(){
        return UserMealsUtil.getWithExceeded(repository.getAll(), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public List<UserMealWithExceed> getAllMealsFilteredByDateAndTime(LocalDate startDate,LocalTime startTime,
                                                                     LocalDate endDate, LocalTime endTime){
        return UserMealsUtil.getFilteredWithExceeded(repository.getAll(),
                LocalDateTime.of(startDate, startTime).toLocalTime(),
                LocalDateTime.of(endDate, endTime).toLocalTime(),
                UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }
    @Override
    public UserMeal save(UserMeal userMeal){
        return ExceptionUtil.check(repository.save(userMeal), userMeal.getId());

    }

    @Override
    public UserMeal get(int id) {
        return repository.get(id);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.check(repository.delete(id), id);
    }



}
