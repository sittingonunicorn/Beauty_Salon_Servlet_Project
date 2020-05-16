package net.ukr.lina_chen.controller;

import net.ukr.lina_chen.controller.command.*;
import net.ukr.lina_chen.model.service.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class Servlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) {
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
        commands.put("login",
                new LoginCommand(new UserService()));
        commands.put("logout",
                new LogoutCommand());
        commands.put("registration",
                new RegistrationCommand(new UserService()));
        commands.put("master",
                new MasterCommand());
        commands.put("admin",
                new AdminCommand());
        commands.put("user",
                new UserCommand());
        commands.put("error",
                new ErrorCommand());
        commands.put("index",
                new IndexCommand());
        commands.put("user/servicetypes",
                new ServicetypesCommand(new ProfessionService()));
        commands.put("user/beautyservices/[0-9]*",
                new BeautyservicesCommand(new BeautyservicesImpl()));
        commands.put("user/masters/[0-9]*/[0-9]*",
                new MastersListCommand(new ProfessionService(), new BeautyservicesImpl()));
        commands.put("user/time/[0-9]*",
                new TimeCommand(new MasterService(), new AppointmentService()));
        commands.put("user/save",
                new SaveCommand());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        String match = commands.keySet().stream().filter(path::matches).findFirst().orElse("error");
//        Command command = commands.getOrDefault(match,
//                new ErrorCommand());
//        command.execute(request, response);
        Command command = commands.getOrDefault(match,
                (r) -> "/WEB-INF/error.jsp");
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/app"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
