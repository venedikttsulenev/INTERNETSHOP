package com.epam.internetshop.controllers;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import com.epam.internetshop.controllers.commands.Command;
import com.epam.internetshop.controllers.commands.LoginCommand;
import com.epam.internetshop.controllers.commands.NoCommand;
import com.epam.internetshop.controllers.commands.RegisterCommand;

public class RequestHelper {

    private static RequestHelper instance = null;
    private HashMap<String, Command> supportedCommands = new HashMap<>();

    private RequestHelper() {
        supportedCommands.put("login", new LoginCommand());
        supportedCommands.put("register", new RegisterCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = supportedCommands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
