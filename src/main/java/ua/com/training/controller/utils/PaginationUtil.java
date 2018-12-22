package ua.com.training.controller.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {
    public Map<String, Integer> calcPaginationParameters(HttpServletRequest request, int rows) {
        RequestParamUtil requestParamUtil = new RequestParamUtil();
        int currentPage =
                Integer.parseInt(requestParamUtil.getOrDefault(request, "currentPage", "1"));

        int recordsPerPage =
                Integer.parseInt(requestParamUtil.getOrDefault(request, "recordsPerPage", "5"));

        int pagesAmount = rows / recordsPerPage;

        if (rows % recordsPerPage > 0) {
            pagesAmount++;
        }

        if (currentPage > pagesAmount) {
            currentPage = pagesAmount;
        }

        int begin = currentPage * recordsPerPage - recordsPerPage;

        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("currentPage", currentPage);
        parameters.put("recordsPerPage", recordsPerPage);
        parameters.put("pagesAmount", pagesAmount);
        parameters.put("begin", begin);

        return parameters;
    }
}
