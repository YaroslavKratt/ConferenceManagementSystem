package ua.com.training.controller.utils;

import ua.com.training.model.services.conference_service.FilterSortType;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class FilterSortUtil {
    public FilterSortType setFilterSortType(HttpServletRequest request) {
        FilterSortType filterSortType;
        if (new ValidationUtil().isNullOrEmpty(request.getParameter("sortType"))) {
            filterSortType = FilterSortType.ALL;
        } else {
            filterSortType = FilterSortType.valueOf(request.getParameter("sortType").toUpperCase());
        }
        return filterSortType;
    }
}
