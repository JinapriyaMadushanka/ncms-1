package lk.spark.sample.controller;

import com.google.gson.JsonObject;
import lk.spark.sample.dao.User;
import lk.spark.sample.service.UserService;
import lk.spark.sample.service.UserServiceImpl;
import lk.spark.sample.utill.ServletConstants;

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
        String userU = req.getParameter(ServletConstants.USER);
        UserService userService = new UserServiceImpl();
        if(userU.equals(ServletConstants.USER_REGISTER)){
            User user = new User(req.getParameter(ServletConstants.USER_NAME),
                    req.getParameter(ServletConstants.PASSWORD),
                    req.getParameter(ServletConstants.NAME),
                    Integer.parseInt(req.getParameter(ServletConstants.IS_MOH)),
                    Integer.parseInt(req.getParameter(ServletConstants.IS_HOSPITAL)));
            String result = userService.registerUser(user);
            sendResponse(result, resp);
        }else if(userU.equals(ServletConstants.LOGIN)){
            String result = userService.loginUser(req.getParameter(ServletConstants.USER_NAME), req.getParameter(ServletConstants.PASSWORD));
            sendResponse(result, resp);
        }

    }

    private void sendResponse(String data, HttpServletResponse resp) throws IOException
    {
        resp.setContentType(ServletConstants.CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty(ServletConstants.RESPONSE_KEY, data);
        writer.print(json.toString());
        writer.flush();
    }
}
