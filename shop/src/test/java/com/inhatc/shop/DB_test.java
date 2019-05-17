package com.inhatc.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import org.junit.Test;

public class DB_test {
@Test
	public void test() throws Exception{
		Class.forName("org.mariadb.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mariadb://114.71.137.109:3306/b_201444074","root","tmvlflt1234");
		System.out.println(con);		
	}
}
