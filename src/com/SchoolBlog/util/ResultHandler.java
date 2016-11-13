package com.SchoolBlog.util;

import java.util.HashMap;
import java.util.Map;

public class ResultHandler {

	private static final String CODE="code";
    public static Map<String,Object> handleJson(String ObjName,Object obj,int code){
	Map<String,Object> result = new HashMap<String,Object>();
	result.put(CODE, code);
	result.put(ObjName, obj);
	return result;
	
    }       
}
