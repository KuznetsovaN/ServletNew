package com.example.newservlet.logic;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Земляничка", "Мармеладка", 7888));
        model.put(2, new User("Астарион", "Плутович", 77777777));
        model.put(3, new User("Гейл", "Дескари", 7777));
        model.put(4, new User("Лазанья", "Зеленая", 55555));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }

    public boolean removeFromList(int id) {
        User removedUser = model.remove(id);
        return removedUser != null;
    }
    public void update(User user,int id) {
        model.put(id, user);
    }
}
