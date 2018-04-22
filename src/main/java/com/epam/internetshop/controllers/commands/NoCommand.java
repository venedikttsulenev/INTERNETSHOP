package com.epam.internetshop.controllers.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.internetshop.controllers.manager.ConfigurationManager;

public class NoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_PAGE_PATH);
    }

}
