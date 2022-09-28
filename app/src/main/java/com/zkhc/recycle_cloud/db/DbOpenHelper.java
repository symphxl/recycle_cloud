package com.zkhc.recycle_cloud.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接
 */
public class DbOpenHelper {
    private static final String CLS = "com.mysql.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://119.45.219.211:3306/ceshi?characterEncoding=utf-8&serverTimezone=UTC";
    private static final String URL = "jdbc:mysql://192.168.10.20:3306/ceshi?characterEncoding=utf-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PWD = "123456";
//    private static final String PWD = "546321";

    public static Connection conn; //连接对象
    public static Statement stmt;//命令集
    public static PreparedStatement pStmt; //预编译命令集
    public static ResultSet rs; //结果集

    //与数据库简历连接
    public static void getConnection() {
        try {
            Class.forName(CLS);
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //关闭数据库连接
    public static void closeAll() {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (pStmt != null) {
                pStmt.close();
                pStmt = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
