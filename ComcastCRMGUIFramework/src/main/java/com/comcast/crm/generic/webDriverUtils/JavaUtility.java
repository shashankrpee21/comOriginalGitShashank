package com.comcast.crm.generic.webDriverUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.mifmif.common.regex.Generex;

public class JavaUtility {

	public int getRandonNumber() {
		Random random = new Random();
		int randomNo = random.nextInt(5000);
		return randomNo;
	}

	public String getEmailID() {
		Generex generexFN = new Generex("[a-z]{6}");
		String fileNumber = generexFN.random();
		return fileNumber + "@email.com";
	}

	/*
	 * =====JUST FOR KNOWLEDGE========== and alternate way to generate EMAIL ID
	 * import java.util.Random;
	 * 
	 * public String getEmailID() { Random random = new Random(); StringBuilder sb =
	 * new StringBuilder(); 
	 * for (int i = 0; i < 4; i++) { 
	 * sb.append((char) ('a' + random.nextInt(26))); // generate random lowercase letter 
	 * }
	 * sb.append("@com.com"); return sb.toString(); 
	 * } 
	 */

	public String getSystemDateYYYYMMDD() {
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateObj);
		return date;
	}

	public String getRequiredDateYYYYMMDD(int days) {
		Date dateObj = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(dateObj);
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sdf.format(cal.getTime());
		return reqDate;
	}

	public String get_Time() {
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		return time;
	}
}
