package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;


@ActiveProfiles({Profiles.JDBC, Profiles.HSQLDB})
public class JdbcUserServiceTest extends UserServiceTest {
}
