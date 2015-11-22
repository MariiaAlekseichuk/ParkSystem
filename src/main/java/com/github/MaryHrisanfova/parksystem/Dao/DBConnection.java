package com.github.MaryHrisanfova.parksystem.dao;

import org.apache.log4j.Logger;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

/** Класс содержит один метод, который создает новое подключение
 * к базе данных
 * @author Маша
 * @since 11.11.2015.
 */
public class DBConnection {
    final static Logger logger = Logger.getLogger(DBConnection.class);
    private static final long serialVersionUID = 1L;

    public static Connection getConnection() {
        DataSource ds = null;

        try {
            Context envCtx = (Context) new InitialContext().lookup("java:/comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/ParkDB");
        } catch (NamingException n) {
            logger.error("Не разрешенное имя" + n.getRemainingName() + ". " + n);
            return null;
        }

        try {
            return ds.getConnection();
        } catch (SQLException e) {
            logger.error("Не удалось поключиться к базе данных:" + e);
            return null;
        }
    }
}
