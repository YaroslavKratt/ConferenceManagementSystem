package ua.com.training.model.services;

import ua.com.training.model.dao.DAO;
import ua.com.training.model.dao.ReportDao;
import ua.com.training.model.dao.jdbc.JdbcDaoFactory;
import ua.com.training.model.dao.jdbc.JdbcReportDao;
import ua.com.training.model.entity.Report;

public class ReportService {
    private ReportDao reportDao = new JdbcDaoFactory().createReportDao();

    public boolean subscribeUserOnReport(long userId, long reportId) {
        setAmountOfSubscribedUsers(reportId, getAmountOfSubscribedUsers(reportId) + 1);
        return reportDao.subscribe(userId, reportId);

    }

    public boolean checkSubscription(long userId, long reportId) {
        return reportDao.checkSubscription(userId, reportId);
    }

    public void unsubscribeUserFromReport(long userId, long reportId) {
        setAmountOfSubscribedUsers(reportId, getAmountOfSubscribedUsers(reportId) - 1);
        reportDao.unsubscribe(userId, reportId);
    }

    public void addNewReportToConference(long conferenceId, Report report) {
        reportDao.addNew(conferenceId, report);
    }

    public void deleteReport(long id) {
        reportDao.delete(id);
    }

    private int getAmountOfSubscribedUsers(long reportId) {
        return reportDao.getAmountOfSubscribedUsers(reportId);
    }

    private void setAmountOfSubscribedUsers(long reportId, int amount) {
        reportDao.setAmountOfSubscribedUsers(reportId, amount);
    }

    public  void setVisitorsAmount(long reportId, int amount) {
        reportDao.setComersAmount(reportId,amount);
    }
    public  void getComersAmount(long reportId) {
        reportDao.getComersAmount(reportId);
    }




}
