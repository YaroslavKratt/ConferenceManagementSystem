package ua.com.training.model.dao;

import ua.com.training.model.entity.Report;

public interface ReportDao extends DAO<Report>{
    boolean subscribe(long userId, long reportId);

    boolean checkSubscription(long userId, long reportId);

    void unsubscribe(long userId, long reportId);

    void addNew(long conferenceId, Report report);
}
