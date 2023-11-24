package com.example.newservlet.logic;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();

    private final Map<Integer, User>model;

    public static Model getInstance(){
        return instance;
    }
    private Model(){
        model = new HashMap<>();
        model.put(1, new User("Пуговка", "Земляничная", 2000.00));
        model.put(2, new User("Звездочка", "Красивая", 999999.00));
        model.put(3, new User("Змейка", "Рыжая", 100000.00));
        model.put(4, new User("Зайка", "Солнышко", 100000.00));
    }
    public void add(User user, int id){
        model.put(id, user);
    }
    public Map<Integer, User> getFromList(){
        return model;
    }
    public boolean removeFromList(int id) {
        User removedUser = model.remove(id);
        return removedUser != null;
    }
    public void edit(int id, User user){
        model.put(id, user);
    }
    public boolean hasUser(int id){
        return model.containsKey(id);
    }

}