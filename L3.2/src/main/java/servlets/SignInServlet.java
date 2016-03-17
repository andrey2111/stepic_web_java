package servlets;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSets.UsersDataSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by andrey on 10.01.2016.
 */
public class SignInServlet extends HttpServlet{
    private final DBService dbService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

//        if (profile == null || !profile.getPass().equals(password)) {
//            response.getWriter().print("Unauthorized");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        response.getWriter().print("Authorized");
//        response.setStatus(HttpServletResponse.SC_OK);
        try {
            UsersDataSet dataSet = dbService.getUserByLogin(login);
            if (dataSet.getPassword() == null || !dataSet.getPassword().equals(password)) {
                response.getWriter().print("Unauthorized");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            response.getWriter().print("Authorized");
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception e) {
            response.getWriter().print("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //e.printStackTrace();
            return;
        }

    }
}
