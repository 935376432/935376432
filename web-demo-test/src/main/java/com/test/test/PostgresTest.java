/*
 *jiji java
 */
package com.test.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class PostgresTest {
    public static void main(String[] args) throws Exception {

        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://10.10.66.47:5432/shterm", "shterm", "shterm");
        DatabaseMetaData metaData = conn.getMetaData();
        System.out.println("Database :" + metaData.getDatabaseProductName() +
            "(" + metaData.getDatabaseProductVersion() +")" );
        conn.close();

    }


}
