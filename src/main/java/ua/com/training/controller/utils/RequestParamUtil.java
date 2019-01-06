package ua.com.training.controller.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class RequestParamUtil {
    private Logger LOG = LogManager.getLogger(RequestParamUtil.class);

    public String getOrDefault(HttpServletRequest request, String param, String byDefault) {
        if (request.getParameter(param) != null) {
            return request.getParameter(param);
        } else {
            return byDefault;
        }
    }

    public boolean nullConferenceParametersPresent(HttpServletRequest request) {
        return isNullOrEmpty(request.getParameter("conference-name-en"))
                || isNullOrEmpty(request.getParameter("conference-name-ua"))
                || isNullOrEmpty(request.getParameter("conference-location"))
                || isNullOrEmpty(request.getParameter("conference-date-time"));

    }

    public boolean nullReportParametersPresent(HttpServletRequest request, String... reportIds) {
        for (String reportId : reportIds) {

            if (isNullOrEmpty(request.getParameter("report-name-en" + reportId))
                    || isNullOrEmpty(request.getParameter("report-name-ua" + reportId))
                    || isNullOrEmpty(request.getParameter("report-date-time" + reportId))
                    || isNullOrEmpty(request.getParameter("report-speaker" + reportId))) {
                return true;
            }
        }

        return false;

    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
