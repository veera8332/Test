package com.ninza.hrm.api.genericutilities;

import java.time.LocalDate;
import java.util.Random;

public class JavaUtility {
	LocalDate currentDate;
	public int getRandomNum() {
		Random rm=new Random();
		int randomnum=rm.nextInt(5000);
		return randomnum;
	}
public String getSystemDateyyyymmdd() {
	 currentDate=LocalDate.now();
	String date=currentDate.toString();
	return date;
}
public String getReqDateyyyymmdd(int days) {
	String endDate= currentDate.plusDays(days).toString();
	return endDate;
}
}
