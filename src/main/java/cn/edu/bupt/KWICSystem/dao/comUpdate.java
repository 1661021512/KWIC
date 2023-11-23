package cn.edu.bupt.KWICSystem.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


public class comUpdate {
    public static void main(String[] args) throws SQLException {
        String content = "BB";
        LocalDateTime now = LocalDateTime.now();
//        String sql = "insert into kwicinput values(null,?,?)";
//        int ResultCount = jdbcUtils.updateDate(sql,"Star war",now);
//        System.out.println(ResultCount);
        String sql = "SELECT content FROM kwicinput Order By time desc";
        List<String> lines = jdbcUtils.queryData(sql);
        System.out.println(lines);
    }
}
