package com.ninza.hrm.api.genericutilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
public String getDataFromPropertiesFile(String key) throws IOException {
	FileInputStream fis=new FileInputStream("./config_env_data/configEnvdata.properties");
	Properties pobj= new Properties();
	pobj.load(fis);
	String data=pobj.getProperty(key);//based on the test case req it will return the value
	return data;//return data back to calling function	
}
}
