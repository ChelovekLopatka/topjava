package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class InMemoryUserReposotoryImpl implements UserRepository {
    Map<Integer, User> repository = new ConcurrentHashMap<>();
    AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        if (user.isNew()){
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        repository.remove(id);
        return repository.containsKey(id);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        for (Map.Entry<Integer, User> pair : repository.entrySet()){
            if (pair.getValue().getEmail().equals(email))
                return pair.getValue();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> usersSortedByName = new ArrayList<>();
        usersSortedByName.addAll(repository.values());

        Collections.sort(usersSortedByName, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        return usersSortedByName;
    }
}
