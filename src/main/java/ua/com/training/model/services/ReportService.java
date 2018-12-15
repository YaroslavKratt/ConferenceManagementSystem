package ua.com.training.model.services;

import ua.com.training.model.dao.DAO;
import ua.com.training.model.dao.ReportDao;
import ua.com.training.model.dao.jdbc.JdbcDaoFactory;
import ua.com.training.model.dao.jdbc.JdbcReportDao;

public class ReportService {
    private ReportDao reportDao = new JdbcDaoFactory().createReportDao();
    public boolean subscribeUserOnReport(long userId, long reportId){
        return reportDao.subscribe(userId, reportId);

    }

    public boolean checkSubscription(long userId, long reportId) {
        return reportDao.checkSubscription(userId, reportId);
    }

    public void unsubscribeUserFromReport(long userId, long reportId) {
        reportDao.unsubscribe(userId, reportId);
    }
}
