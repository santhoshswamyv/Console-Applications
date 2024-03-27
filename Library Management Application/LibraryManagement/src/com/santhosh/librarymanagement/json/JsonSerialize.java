package com.santhosh.librarymanagement.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class JsonSerialize {

	private static JsonSerialize jsonSerialize;

	private JsonSerialize() {
	}

	public static JsonSerialize getJsonSerialize() {
		if (jsonSerialize == null) {
			jsonSerialize = new JsonSerialize();
		}
		return jsonSerialize;
	}

	public <T> void serialize(T t, String path) {
		Gson gson = new Gson();
		String json = gson.toJson(t);
		try (PrintWriter writer = new PrintWriter(path)) {
			writer.println(json);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public <T> T deserialize(String path, Class<T> t) {
		Gson gson = new Gson();
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			StringBuilder jsonBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonBuilder.append(line);
			}
			String json = jsonBuilder.toString();
			return gson.fromJson(json, t);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}

	public <T> T deserialize(String path, TypeToken<T> typeToken) {
		Gson gson = new Gson();
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			StringBuilder jsonBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonBuilder.append(line);
			}
			String json = jsonBuilder.toString();
			return gson.fromJson(json, typeToken.getType());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
}