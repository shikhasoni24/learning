package com.learning.core.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ReadHttpURLs {
	private static HttpURLConnection connection;

	public static void main(String[] args) throws IOException {

		List<String> urls = new ArrayList<>();
		// urls.add("https://stackoverflow.com");
		// urls.add("https://www.google.com/");
		// urls.add("https://www.facebook.com/");
		// urls.add("https://www.w3schools.com/");
		// urls.add("https://www.javatpoint.com");
		List<String> list = new ArrayList<>();
		File file = new File("D:\\shikha\\URLs.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));
		int st;
		StringBuilder str = new StringBuilder();

		while ((st = br.read()) != -1) {
			System.out.println((char) st);
			if (st == ',') {
				urls.add(str.toString());
				str = new StringBuilder();

			} else {
				str.append((char) st);
			}
		}
		urls.add(str.toString());
		System.out.println(urls);

		for (String url : urls) {

			try {
				URL httpurl = new URL(url);
				connection = (HttpURLConnection) httpurl.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Content-Type", "application/json");
				// connection.setRequestProperty("User-Agent", "Mozilla/5.0(WindowsNT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, likeGecko)Chrome/65.0.3325.181 Safari/537.36");
				System.out.println(connection.getResponseCode());
				if (connection.getResponseCode() != 200) {
					list.add(url);

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println(list);
	}
}
