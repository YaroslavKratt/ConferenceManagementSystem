package ua.com.training.model.services.conference_service;

import ua.com.training.model.entity.Conference;

import java.util.List;

public interface PaginatedFilterSort {
    List<Conference> filterSort();
}
