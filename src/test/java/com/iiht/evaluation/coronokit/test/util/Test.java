package com.iiht.evaluation.coronokit.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		int randomNumber = (int) (Math.random()*90000);
		System.out.println(randomNumber);
	}

}
