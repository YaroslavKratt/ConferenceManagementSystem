package ua.com.training.model.dao;

import ua.com.training.model.dao.jdbc.JdbcDaoFactory;

public abstract class DaoFactory {
    private static volatile DaoFactory daoFactory;

    public abstract UserDao createUserDao();

    public abstract ReportDao createReportDao();

    public abstract SpeakerDao createSpeakerDao();

    public abstract ConferenceDao createConferenceDao();

   static public DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JdbcDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

