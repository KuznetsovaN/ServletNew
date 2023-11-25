package com.example.newservlet;

import com.example.newservlet.logic.Model;
import com.example.newservlet.logic.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        JsonObject jsonObject1 = gson.fromJson("{\nid: " + request.getParameter("id") + "\n}", JsonObject.class);
        int id = jsonObject1.get("id").getAsInt();

        response.setContentType("application/json;charset=utf-8");

        PrintWriter pw = response.getWriter();

        // в этом куске ниже была проблемом с выдаче данных при поиске
        if (id == 0) {
            pw.print(gson.toJson(model.getFromList()));
        } else if (id > 0) {
            if (model.getFromList().containsKey(id)) {
                pw.print(gson.toJson(model.getFromList().get(id)));
            } else {
                pw.print(gson.fromJson("{\n\"Ошибка\" : \"Такого пользователя нет\"\n}", JsonObject.class));
            }
        } else {
            pw.print(gson.fromJson("{\n\"Ошибка\" : \"ID должен быть больше нуля\"\n}", JsonObject.class));
        }
    }
}