package com.comcast.crm.leadsTest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webDriverUtility.UtilityClassObject;

@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementationClass.class)
public class CreateLeadTest extends BaseClass{

	@Test
	public void CreateLead() throws Throwable {
		
		// read testScript data from Excel file
		UtilityClassObject.getTest().log(Status.INFO, "data from Excel file");
//		String orgName = elib.getDataFromExcel("org", 1, 2)+jlib.getRandonNumber();
//		String descriptionInfo = elib.getDataFromExcel("org", 1, 6);
//		String email = elib.getDataFromExcel("leads", 4, 7)+jlib.getEmailID();
//		System.out.println(email);
	}

}
