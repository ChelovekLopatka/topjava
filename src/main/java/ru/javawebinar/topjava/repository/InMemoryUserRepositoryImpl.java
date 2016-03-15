package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    Map<Integer, User> repository = new ConcurrentHashMap<>();
    AtomicInteger counter = new AtomicInteger(0);
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        repository.remove(id);
        return repository.containsKey(id);
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()){
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> usersSortedByName = new ArrayList<>();
        usersSortedByName.addAll(repository.values());

        Collections.sort(usersSortedByName, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        return usersSortedByName;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        for (Map.Entry<Integer, User> pair : repository.entrySet()){
            if (pair.getValue().getEmail().equals(email))
                return pair.getValue();
        }
        return null;
    }
}
