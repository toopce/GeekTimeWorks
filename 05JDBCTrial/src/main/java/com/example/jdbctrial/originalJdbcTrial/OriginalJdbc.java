package com.example.jdbctrial.originalJdbcTrial;

import java.sql.*;

public class OriginalJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String URL = "jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&amp;characterEncoding=utf-8";
        String USER = "root";
        String PASSWORD = "12345678";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL,USER,PASSWORD);
        conn.setAutoCommit(false);
        //使用statement
//        Statement st = conn.createStatement();
        //查
//        ResultSet rs = st.executeQuery("select * from try_in_idea");
//        while (rs.next()){
//            System.out.println(rs.getString("id"));
//        }
//        rs.close();
        //增
        String sql = "inser into try_in_idea (id,name) value(1,'2')";
        PreparedStatement ps = conn.prepareStatement(sql);
        try {

            ps.execute();
            conn.commit();

    }catch (Exception e){
            conn.rollback();

        }finally {
            ps.close();
            conn.close();
        }



    }
}
