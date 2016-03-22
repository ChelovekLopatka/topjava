package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

@ContextConfiguration(locations = {"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    private UserMealRestController controller;

    @Autowired
    private DbPopulator dbPopulator;

    //TODO TESTS ON NOT OWN MEALS THAT EXPECT NFE
    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        controller.get(100000);
    }

    @Test
    public void testDelete() throws Exception {
        controller.delete(100000);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        controller.getBetween(LocalDate.now(), LocalTime.now(), LocalDate.now(), LocalTime.now());
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        controller.getBetween(LocalDate.now(), LocalTime.now(), LocalDate.now(), LocalTime.now());
    }

    @Test
    public void testGetAll() throws Exception {
        controller.getAll();
    }

    @Test
    public void testUpdate() throws Exception {
         controller.update(new UserMeal(LocalDateTime.now(), "bkbkb", 1000), 100000);
    }

    @Test
    public void testSave() throws Exception {
        controller.update(new UserMeal(LocalDateTime.now(), "bkbkb", 1000), 100000);
    }
}