package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {

    @Autowired
    private UserMealService service;

    public void delete(int id){
        service.delete(id);
    }

    public UserMeal save(UserMeal userMeal){
        service.save(userMeal);
        return userMeal;
    }

    public List<UserMealWithExceed> getAllMealsFilteredByDateAndTime(LocalDate startDate, LocalTime startTime,
                                                                     LocalDate endDate, LocalTime endTime){
        return service.getAllMealsFilteredByDateAndTime(startDate, startTime, endDate, endTime);
    }

    public List<UserMealWithExceed> getAll(){
        return service.getAll();
    }

    public UserMeal get(int id){
        return service.get(id);
    }






}
