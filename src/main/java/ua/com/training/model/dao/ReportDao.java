package ua.com.training.model.dao;

import ua.com.training.model.dto.ReportDTO;
import ua.com.training.model.entity.Report;

public interface ReportDao extends DAO<Report>{
    boolean subscribe(long userId, long reportId);

    boolean isSubscribed(long userId, long reportId);

    void unsubscribe(long userId, long reportId);

    void addNew(long conferenceId, Report report);

    void addNew(long conferenceId, ReportDTO report);

    int getAmountOfSubscribedUsers(long reportId);

    void setAmountOfSubscribedUsers(long reportId, int amount);

    void setComersAmount(long reportId, int amount);

    int getComersAmount(long reportId);
}
