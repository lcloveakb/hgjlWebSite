package com.senseId.social.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.senseId.social.entity.Article;

/**
 * 类型转换工具类
 * 
 * @author Liu Chang,2015-6-11
 * 
 */
public class TypeTransform {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");

	public static Date string2Date(String str) {

		Date date = new Date();
		try {
			date = sdf.parse(str);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String dateFormat(Date date) {

		String str = "";
		str = sdf.format(date);
		return str;
	}

	public static String list2Json(ArrayList<Article> list) throws IOException {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:sss")
				.create();
		String json = gson.toJson(list);
		return json;
	}
	
	public static String articleList2Json(ArrayList<Article> list) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:sss")
				.create();
		String json = gson.toJson(list, new TypeToken<ArrayList<Article>>() {}.getType());
		return json;
	}
}
