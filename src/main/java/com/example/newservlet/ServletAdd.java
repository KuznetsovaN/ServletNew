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

//@WebServlet (urlPatterns = "/add")
//public class ServletAdd extends HttpServlet {
//    private AtomicInteger counter = new AtomicInteger(5);
//    Model model = Model.getInstance();
//    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//   /* protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        double salary = Double.parseDouble(request.getParameter("salary"));
//
//        User user = new User(name, surname, salary);
//        model.add(user, counter.getAndIncrement());
//
//        pw.print("<html>" +
//                "<h3>Пользователь " + name + " " + surname + " с зарплатой= " + salary + " успешно создан! (:</h3>" +
//                "<a href=\"addUser.html\">Создать нового пользователя</a><br/>" +
//                "<a href=\"index.jsp\">Домой</a>" +
//                "</html>");
//    }*/
//
//    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("UTF-8");
//        StringBuffer jb = new StringBuffer();
//        String line;
//        try {
//            BufferedReader reader = request.getReader();
//            while ((line = reader.readLine()) != null) {
//                jb.append(line);
//            }
//        } catch (Exception e) {
//            System.out.println("Error");
//        }
//        System.out.println(jb);
//        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
//
//
//
//        String name = jobj.get("name").getAsString();
//        String surname = jobj.get("surname").getAsString();
//        double salary = jobj.get("salary").getAsDouble();
//
//        User user = new User(name, surname, salary);
//        model.add(user, counter.getAndIncrement());
//
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter pw = response.getWriter();
//
//        pw.print(gson.toJson(model.getFromList()));
//    }
//}

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {
    private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        model.add(user, counter.getAndIncrement());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(gson.toJson(model.getFromList()));
    }
}