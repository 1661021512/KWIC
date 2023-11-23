package cn.edu.bupt.KWICSystem.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class jdbcUtils {

    /**
     * @return java.sql.Connection
     * @Description 获取数据库连接
     * @Param []
     **/
    public static Connection getSqlConnection() throws Exception {

        //2、读取配置信息
        final String url = "jdbc:mysql://localhost:3306/kwic?characterEncoding=utf8&useSSL=false";
        final String user = "root";
        final String password = "1661021512";
        final String driverClass = "com.mysql.cj.jdbc.Driver";

        //3、加载驱动
        Class.forName(driverClass);

        //4、获取连接
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    /**
     * @return void
     * @Description 关闭连接和Statement资源
     * @Param [conn, ps]
     **/
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //重载

    /**
     * @return void
     * @Description 通用增删改操作
     * @Param [sql, args]
     **/
    public static int updateDate(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取数据库连接
            conn = getSqlConnection();
            //预编译sql语句，获取PreparedStatement实例
            ps = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //执行操作
            return ps.executeUpdate(); //返回操作的字段数，没有则为0
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            closeResource(conn, ps);
        }
        return 0;
    }

    public static List<String> queryData(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //获取数据库连接
            conn = getSqlConnection();
            //预编译sql语句，获取PreparedStatement实例
            stmt = conn.prepareStatement(sql);
            //填充占位符
            for (int i = 0; i < args.length; i++) {
                stmt.setObject(i + 1, args[i]);
            }
            ResultSet SqlResult = stmt.executeQuery();
            //TODO 此处当未查找到数据时可以设置一个异常，目前是直接返回一个null
            List<String> ListResults = new ArrayList<String>();
            String ListResult = null;
            while (SqlResult.next()) {
                ListResult = SqlResult.getString("content");
                ListResults.add(ListResult);
            }
            return ListResults;
            //执行操作
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            closeResource(conn, stmt);
        }
        return null;
    }
}





