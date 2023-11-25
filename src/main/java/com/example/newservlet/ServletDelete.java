package com.example.newservlet;

import com.example.newservlet.logic.Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        JsonObject jsonObject = gson.fromJson(String.valueOf(jb), JsonObject.class);
        int id = jsonObject.get("id").getAsInt();

        response.setContentType("application/json;charset=utf-8");

        PrintWriter pw = response.getWriter();
        boolean isValidId = model.getFromList().containsKey(id);

        if (model.getFromList().isEmpty()) {
            pw.print(gson.fromJson("{\n\"Модель\" : \"пуста\"\n}", JsonObject.class));
        } else if (!isValidId) {
            pw.print(gson.fromJson("{\n\"Вы ввели id, которого нет\" : \"" + id + "\"\n}", JsonObject.class));
        } else {
            pw.print(gson.fromJson("{\n\"Пользователь с ID\" : \"" + id + " успешно удален\"\n}", JsonObject.class));
            model.getFromList().remove(id);
        }
    }
}