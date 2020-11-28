package com.scale.bat.context;

import java.util.HashMap;
import java.util.Map;

import com.scale.bat.businessPages.ProductDetailPage;
//import com.scale.bat.framework.utility.AwsS3Service;
import com.scale.bat.framework.utility.JsonParser;
import com.scale.bat.framework.utility.PostgresSqlConnection;

public class ScenarioContext {
	public String ScenarioDataFilePath = System.getProperty("user.dir") + "\\TestData\\ScenarioData.json";
	public Map<String, Object> productDetails;
    private Map<String, Object> scenarioContext;
    public JsonParser jsonParser;
    public PostgresSqlConnection postgresSqlConnection;
    //public AwsS3Service awsS3Service;
    public ScenarioContext(){
    	productDetails = new HashMap<String, Object>();
    	scenarioContext = new HashMap<String, Object>();
    	jsonParser = new JsonParser();
    	postgresSqlConnection = new PostgresSqlConnection();
    	//awsS3Service = new AwsS3Service();
     }

    public void setContext(Map<String, Object> map) {
        scenarioContext.putAll(map);
    }

    public String getContext(String key){
        return String.valueOf(scenarioContext.get(key));
    }

    public void setKeyValue(String key, String value){
        scenarioContext.put(key, value);
    }

    public Boolean isContains(String key){
        return scenarioContext.containsKey(key);
    }

    public void clearContext()
    {
        scenarioContext.clear();
    }

    public Map<String, Object> getScenarioContext(){
        return scenarioContext;
    }

}
 
