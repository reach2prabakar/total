package com.toptal.processor;

import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;

import java.io.IOException;

interface Services {

    void setBody(String body);

    void setHeaders() ;

    void setParameter(JsonObject jsonObject) ;

    void setRequestMethod(String method);

    void setServiceEndPoint(String endPoint);

    void setServiceUri(String uri);

    JsonPath getServiceResponse() ;

    void runServiceCall(String apiName) throws IOException;
}
