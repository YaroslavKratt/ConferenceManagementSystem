package ua.com.training.model.dao.jdbc;

import ua.com.training.model.dao.ReportDao;
import ua.com.training.model.entity.Report;

import java.util.List;

public class JdbcReportDao implements ReportDao {

    @Override
    public Report getById() {
        return null;
    }

    @Override
    public List<Report> getAll() {
        return null;
    }

    @Override
    public void update(Report item) {

    }

    @Override
    public void delete(Report item) {

    }

    @Override
    public boolean addNew(Report item) {
        return false;
    }
}
