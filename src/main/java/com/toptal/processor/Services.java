package com.toptal.processor;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;

import java.io.IOException;

interface Services {

    void setBody(Object body);

    void setHeaders(JsonObject jsonObject) ;

    void setParameter(JsonObject jsonObject,String token) ;

    void setRequestMethod(String method);

    void setServiceEndPoint(String endPoint);

    void setServiceUri(String uri);

    JsonPath getServiceResponse() ;

    void runServiceCall(String apiName) throws IOException;

    void setResponseCode(String responseCode);
}
