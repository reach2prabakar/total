package com.toptal.processor;

import org.json.simple.JSONObject;

import java.util.Map;

public class DataHandlerThread {

    private JSONObject jsonObject;
    static Map<String, Object> dynamicMap;

    private DataHandlerThread(){
    }

    public JSONObject getDataHandler() { return jsonObject;}

    public void setDataHandler(JSONObject jsonObject){this.jsonObject = jsonObject; }

    private static ThreadLocal<DataHandlerThread> dataHandlerThreadlocal=new ThreadLocal<DataHandlerThread>(){
        @Override
        protected DataHandlerThread initialValue(){
            return new DataHandlerThread();
        }
    };

    public static DataHandlerThread getCurrentThread(){return dataHandlerThreadlocal.get(); }

    public static JSONObject getCurrentDataHandler(){return getCurrentThread().getDataHandler(); }

    public static void setCurrentDataHandler(JSONObject jsonObject){
        getCurrentThread().setDataHandler(jsonObject);
    }

    public static void removeCurrentThread(){dataHandlerThreadlocal.remove(); }

}
