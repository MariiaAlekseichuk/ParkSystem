package com.github.MaryHrisanfova.parksystem.dao;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
/**
 * Created by Маша on 11.11.2015.
 */
public class DBConnection {
    private static final long serialVersionUID=1L;

    public static Connection getConnection() throws SQLException, NamingException	{
        DataSource ds=null;

        Context envCtx = (Context) new InitialContext().lookup("java:/comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/ParkDB");
        return ds.getConnection();


    }
}
