package lk.spark.sample.controller;

import com.google.gson.JsonObject;
import lk.spark.sample.dao.User;
import lk.spark.sample.service.UserService;
import lk.spark.sample.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userU = req.getParameter("user");
        UserService userService = new UserServiceImpl();
        if(userU.equals("register")){
            User user = new User(req.getParameter("userName"),
                    req.getParameter("password"),
                    req.getParameter("name"),
                    Integer.parseInt(req.getParameter("moh")),
                    Integer.parseInt(req.getParameter("hospital")));
            String result = userService.registerUser(user);
            sendResponse(result, resp);
        }else if(userU.equals("login")){
            String result = userService.loginUser(req.getParameter("userName"), req.getParameter("password"));
            sendResponse(result, resp);
        }

    }

    private void sendResponse(String data, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("Response", data);
        writer.print(json.toString());
        writer.flush();
    }
}
