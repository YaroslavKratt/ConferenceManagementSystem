package ua.com.training.controller.commands;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CatalogCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Map<String,Integer> map = new HashMap<>();
        map.put("kek", 3);
        request.setAttribute("map", map);
        return PATH_BUNDLE.getString("page.catalog");
    }
}
