package com.toptal.processor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.text.StringSubstitutor;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ApiService implements Services {

    private String serviceUri;
    private String serviceEndpoint;
    private String requestBody;
    private Map<String, Object> requestHeaders;

    private Map<String, Object> requestparameters;

    private String requestMethod;
    private JsonPath jsonPath;

    @Override
    public void setBody(String body) {
        requestBody = body;
    }

    public String getBody() {
        return requestBody;
    }

    @Override
    public void setServiceUri(String uri){ this.serviceUri = uri; }

    public String getServiceUri() {
        return serviceUri;
    }

    @Override
    public void setHeaders() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        requestHeaders = headers;
    }

    @Override
    public void setParameter(JsonObject jsonObject) {
        Map<String, Object> parameter = new HashMap<>();
        Set<String> keys = jsonObject.keySet();
        for(String key : keys){
            if(key.equalsIgnoreCase( "access_token")){
                String access_token = System.getProperty("access_token")!=null? System.getProperty("access_token") :jsonObject.get(key).toString();
                parameter.put(key,access_token);
            }else{
                parameter.put(key,jsonObject.get(key));
            }
        }
        requestparameters = parameter;
    }
    public Map<String, Object> getHeaders(){
        return requestHeaders;
    }

    public Map<String, Object> getParam(){
        return requestparameters;
    }

    @Override
    public void setRequestMethod(String method) {
        requestMethod = method;
    }

    public String getRequestMethod(){
        return  requestMethod;
    }

    @Override
    public void setServiceEndPoint(String endpoint){
        serviceEndpoint = endpoint;
    }

    public String getServiceEndPoint(){
        return serviceEndpoint;
    }

    @Override
    public JsonPath getServiceResponse() {
        return jsonPath;
    }

    @Override
    public void runServiceCall(String apiName){
        Response serviceResponse;
        JSONObject jsonObject = DataHandlerThread.getCurrentDataHandler();
        JsonObject response;
        try {
            response = JsonParser.parseString(jsonObject.get(apiName).toString()).getAsJsonObject();
        }catch (Exception e){
            throw new RuntimeException("The Api name given in the feature file example data does not exist" + e);
        }
        try {
            StringSubstitutor sub = new StringSubstitutor();
            serviceUri = sub.replace(jsonObject.get("uri"));
            serviceEndpoint= sub.replace(response.get("endpoint").getAsString());
            requestBody = sub.replace(response.get("requestBody").toString());
            requestMethod = sub.replace(response.get("method").getAsString());
            JsonObject reqParam = response.get("parameters").getAsJsonObject();
            setParameter(reqParam);
            setServiceEndPoint(serviceEndpoint);
            setRequestMethod(requestMethod);
            setHeaders();
            setBody(requestBody);
        } catch (NullPointerException e) {
            throw new RuntimeException(e + "The jsonTestData.json is missing /mismatch one of the key =>'endpoint,baseURI,method,requestBody,header' please update");
        }
        serviceResponse =  getRequestMethod().equalsIgnoreCase("get")?
                given().baseUri(getServiceUri()).basePath(getServiceEndPoint()).headers(getHeaders()).params(getParam()).get() :
                given().baseUri(getServiceUri()).basePath(getServiceEndPoint()).headers(getHeaders()).params(getParam()).body(getBody()).post();
        assertThat("Response status was not 200 for api "+apiName, serviceResponse.getStatusCode(), equalTo(200));
    }
}