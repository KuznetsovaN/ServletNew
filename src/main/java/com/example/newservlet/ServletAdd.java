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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        Map<String, String> requestMap = new HashMap<>();
        StringBuffer jb = new StringBuffer();
        JsonObject jsonObject;

        if (request.getParameter("name") == null) {
            String line;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    jb.append(line);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            jsonObject = gson.fromJson(String.valueOf(jb), JsonObject.class);
        } else {
            requestMap.put("name", request.getParameter("name"));
            requestMap.put("surname", request.getParameter("surname"));
            requestMap.put("salary", request.getParameter("salary"));

            jsonObject = gson.fromJson(String.valueOf(requestMap), JsonObject.class);
        }
        request.setCharacterEncoding("UTF-8");
        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        double salary = jsonObject.get("salary").getAsDouble();
        User user = new User(name, surname, salary);
        model.addUser(user);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(model.getFromList()));
    }
}