package com.example.newservlet.logic;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Model implements Serializable {

    private static final Model instance = new Model();
    private final Map<Integer, User> users;
    private final AtomicInteger nextId;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        users = new HashMap<>();

        users.put(1, new User("Земляничка", "Мармеладка", 7888));
        users.put(2, new User("Астарион", "Плутович", 77777777));
        users.put(3, new User("Гейл", "Дескари", 7777));
        users.put(4, new User("Лазанья", "Зеленая", 55555));

        nextId = new AtomicInteger(5);
    }

    public void addUser(User user) {
        int newId = nextId.getAndIncrement();
        users.put(newId, user);
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public void updateUser(int id, User user){
        if (!users.containsKey(id)) {
            System.out.println("Такой пользователь неизвестен");
        }
        users.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return users;
    }

}