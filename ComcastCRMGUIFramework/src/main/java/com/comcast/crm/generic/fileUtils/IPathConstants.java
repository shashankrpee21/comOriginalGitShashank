package com.comcast.crm.generic.fileUtils;

import java.io.File;
import java.nio.file.Paths;

public interface IPathConstants {


	String filePath = "./src/test/resources/configAppData/commonData.properties";	
	String excelPath = "./src/test/resources/testData/testScriptData.xlsx";	
	String jsonPath ="./configAppData/jsonCommonData.json";	
	String dbURL = "jdbc:mysql://49.249.28.218:3333/ninza_hrm";
	String dbUN = "root";
	String dbPWD = "root";	
	String uploadFile = "C:\\WorkSpace\\ComcastCRMGUIFramework\\src\\test\\resources\\testFiles\\Test.jpg";

/*		
	// for Cross-Platform Compatibility- Construct file paths in a platform-independent way so used Paths.set
	// Use Paths.get() to construct an absolute path dynamically
	
    String filePath = Paths.get(".", "src", "test", "resources", "configAppData", "commonData.properties").toString();
    String excelPath = Paths.get(".", "src", "test", "resources", "testData", "testScriptData.xlsx").toString();
    String jsonPath = Paths.get(".", "configAppData", "jsonCommonData.json").toString();
    String dbURL = "jdbc:mysql://49.249.28.218:3333/ninza_hrm";
    String dbUN = "root";
    String dbPWD = "root";
    String uploadFile = Paths.get(".", "src", "test", "resources", "testFiles", "Test.jpg").toString();

    
    String filePath = Paths.get("src", "test", "resources", "configAppData", "commonData.properties").toAbsolutePath().toString();
    String excelPath = Paths.get("src", "test", "resources", "testData", "testScriptData.xlsx").toAbsolutePath().toString();
    String jsonPath = Paths.get("configAppData", "jsonCommonData.json").toAbsolutePath().toString();
    String dbURL = "jdbc:mysql://49.249.28.218:3333/ninza_hrm";
    String dbUN = "root";
    String dbPWD = "root";
    //String uploadFile = Paths.get("src", "test", "resources", "testData", "Test.jpg").toAbsolutePath().toString();
    String uploadFile = "." + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testData" + File.separator + "Test.jpg";
*/
}
