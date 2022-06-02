package com.toptal.processor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.text.StringSubstitutor;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ApiService implements Services {

    Logger logger = LoggerFactory.getLogger(ApiService.class);
    private String serviceUri;
    private String serviceEndpoint;
    private Object requestBody;
    private Map<String, String> requestHeaders;

    private Map<String, String> requestparameters;

    private Map<String, String> responseToCapture;
    private Map<String, String> responseToValidate;
    private String requestMethod;

    private String responseCode;

    private JsonPath jsonPath;

    @Override
    public void setBody(Object body) {
        requestBody = body;
    }

    public Object getBody() {
        return requestBody;
    }

    @Override
    public void setServiceUri(String uri) {
        this.serviceUri = uri;
    }

    @Override
    public void setResponseCode(String responsecode) {
        responseCode = responsecode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getServiceUri() {
        return serviceUri;
    }

    public Map<String, String> getresponseToCapture() {
        return responseToCapture;
    }

    @Override
    public void setHeaders(JsonObject jsonObject) {
        Map<String, String> headers = new HashMap<>();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            headers.put(key, jsonObject.get(key).getAsString());
        }
        requestHeaders = headers;
    }

    public void setresponseToCapture(JsonObject jsonObject) {
        Map<String, String> responseTocapture = new HashMap<>();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            responseTocapture.put(key, jsonObject.get(key).getAsString());
        }
        responseToCapture = responseTocapture;
    }

    public void setresponseToValidate(JsonObject jsonObject) {
        Map<String, String> responseTovali = new HashMap<>();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            responseTovali.put(key, jsonObject.get(key).getAsString());
        }
        responseToValidate = responseTovali;
    }

    @Override
    public void setParameter(JsonObject jsonObject, String token) {
        Map<String, String> parameter = new HashMap<>();
        Set<String> keys = jsonObject.keySet();
        parameter.put("access_token", token);
        for (String key : keys) {
            if (key.equalsIgnoreCase("access_token")) {
                String access_token = System.getProperty("access_token") != null ? System.getProperty("access_token") : jsonObject.get(key).toString().replaceAll("\"", "");
                parameter.put(key, access_token);
            } else {
                parameter.put(key, jsonObject.get(key).getAsString().replaceAll("\"", ""));
            }
        }
        requestparameters = parameter;
    }

    public Map<String, String> getHeaders() {
        return requestHeaders;
    }

    public Map<String, String> getParam() {
        return requestparameters;
    }

    @Override
    public void setRequestMethod(String method) {
        requestMethod = method;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    @Override
    public void setServiceEndPoint(String endpoint) {
        serviceEndpoint = endpoint;
    }

    public String getServiceEndPoint() {
        return serviceEndpoint;
    }

    @Override
    public JsonPath getServiceResponse() {
        return jsonPath;
    }

    @Override
    public void runServiceCall(String apiName) {
        Response serviceResponse;
        JSONObject jsonObject = DataHandlerThread.getCurrentDataHandler();
        JsonObject response;
        boolean validation = false;
        try {
            response = JsonParser.parseString(jsonObject.get(apiName).toString()).getAsJsonObject();
        } catch (Exception e) {
            logger.error("The Api name given in the feature file example data does not exist" + e);
            throw new RuntimeException("The Api name given in the feature file example data does not exist" + e);
        }
        try {
            StringSubstitutor sub = new StringSubstitutor(BaseTest.apiMap);
            serviceUri = sub.replace(jsonObject.get("uri"));
            String access_token = sub.replace(jsonObject.get("access_token"));
            serviceEndpoint = sub.replace(response.get("endpoint").getAsString());
            requestBody = sub.replace(response.get("requestBody").getAsJsonObject());
            requestMethod = sub.replace(response.get("method").getAsString());
            JsonObject reqParam = response.get("parameters").getAsJsonObject();
            JsonObject reqHeader = response.get("header").getAsJsonObject();
            JsonObject responseToCapture = response.get("responseToCapture").getAsJsonObject();
            if(response.get("tovalidate")!=null){
                JsonObject responseToValidate = response.get("tovalidate").getAsJsonObject();
                setresponseToValidate(responseToValidate);
                validation = true;
            }
            responseCode = response.get("responsecode").toString();
            setServiceEndPoint(serviceEndpoint);
            setRequestMethod(requestMethod);
            setBody(requestBody);
            setResponseCode(responseCode);
            setParameter(reqParam, access_token);
            setHeaders(reqHeader);
            setresponseToCapture(responseToCapture);
        } catch (NullPointerException e) {
            logger.error(e + "The jsonTestData.json is missing /mismatch one of the key =>'endpoint,baseURI,method,requestBody,header' please update");
            throw new RuntimeException(e + "The jsonTestData.json is missing /mismatch one of the key =>'endpoint,baseURI,method,requestBody,header' please update");
        }
        logger.debug("request generated to execute is :"+response);
        serviceResponse = getRequestMethod().equalsIgnoreCase("get") ?
                given().baseUri(getServiceUri()).basePath(getServiceEndPoint()).headers(getHeaders()).queryParams(getParam()).get() :
                getRequestMethod().equalsIgnoreCase("post") ?
                        given().baseUri(getServiceUri()).basePath(getServiceEndPoint()).headers(getHeaders()).queryParams(getParam()).body(getBody()).post() :
                        getRequestMethod().equalsIgnoreCase("patch") ?
                                given().baseUri(getServiceUri()).basePath(getServiceEndPoint()).headers(getHeaders()).queryParams(getParam()).body(getBody()).patch():
                                given().baseUri(getServiceUri()).basePath(getServiceEndPoint()).headers(getHeaders()).queryParams(getParam()).body(getBody()).delete();
                                logger.debug("request:" + response + "-----> response ----> " + serviceResponse.jsonPath());
        JsonPath jpath = serviceResponse.getBody().jsonPath();
        logger.debug("api name ---> "+apiName +" json response body -> :"+jpath);
        for (String key : getresponseToCapture().keySet()) {
            String resKeyValue = jpath.get(key);
            BaseTest.apiMap.put(getresponseToCapture().get(key), resKeyValue);
        }
        assertThat("Response status was not 200 for api " + apiName, serviceResponse.getStatusCode(), equalTo(Integer.valueOf(getResponseCode())));
        logger.debug("Response status was not 200 for api " + apiName, serviceResponse.getStatusCode(), equalTo(Integer.valueOf(getResponseCode())));
        if(validation){
            for (String key : responseToValidate.keySet()) {
                String resKeyValue = jpath.get(key);
               String expectedResponse =  responseToValidate.get(key);
               if(expectedResponse.equals("null")){
                   expectedResponse = null;
               }
                assertThat("Response message is not matched for API ->" + apiName, resKeyValue, equalTo(expectedResponse));
                logger.debug("Response message is not matched for API ->" + apiName, resKeyValue, equalTo(expectedResponse));
            }
        }
        logger.debug(apiName + "Api is passed");
    }
}