package com.finup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author huangrongchao on 2018/5/25.
 * @version 1.0
 */
public class SelectTest extends BaseTest {
    @Autowired
    DataSource dataSource;

    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(" select * from poll_data");
        resultSet.next();
        System.out.println(resultSet.getString(1));
    }
}
