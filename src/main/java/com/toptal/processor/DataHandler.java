package com.toptal.processor;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;

public class DataHandler {
    static Logger logger = LoggerFactory.getLogger(DataHandler.class);

    static JSONObject jsonObject;

    public static JSONObject getjsonObject() {
        return jsonObject;
    }

    public static JSONObject readJson() {
        jsonObject =  readJsonFile(System.getProperty("user.dir") + "/src/test/resources/jsonTestData.json");
        return jsonObject;
    }

    public static JSONObject readJsonFile(String path) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonContent ;

        try {
            FileReader reader = new FileReader(path);
            Throwable var4 = null;

            try {
                Object obj = jsonParser.parse(reader);
                jsonContent = (JSONObject) obj;
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (var4 != null) {
                    try {
                        reader.close();
                    } catch (Throwable var13) {
                        var4.addSuppressed(var13);
                    }
                } else {
                    reader.close();
                }
            }
        } catch (ParseException | IOException var16) {
            logger.error("\"Failed to read file :\" + path, var16");
            throw new RuntimeException("Failed to read file :" + path, var16);
        }
        logger.debug(jsonContent.toString());
        return jsonContent;
    }
}