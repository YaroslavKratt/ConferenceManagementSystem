package ua.com.training.controller.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestParamUtil {
    public String getOrDefault(HttpServletRequest request, String param, String byDefault) {
        if(request.getParameter(param) != null) {
            return request.getParameter(param);
        }
        else {
            return byDefault;
        }
    }
}
