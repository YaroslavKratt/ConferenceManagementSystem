package ua.com.training.model.dao.jdbc;

import ua.com.training.model.dao.*;

public class JdbcDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao();
    }

    @Override
    public ReportDao createReportDao() {
        return new JdbcReportDao();
    }

    @Override
    public SpeakerDao createSpeakerDao() {
        return new JdbcDaoSpeaker();
    }

    @Override
    public ConferenceDao createConferenceDao() {
        return new JdbcConferenceDao();
    }


}
