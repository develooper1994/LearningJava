package com.Network;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static java.lang.System.out;

public class UrlDemo {

    static void UrlChecks(){
        try {
            URL url = new URL("https://github.com/develooper1994/LearningJava/index.htm?language=en#j2se");
            out.println("URL is " + url);
            out.println("getProtocol: " + url.getProtocol());
            out.println("getAuthority: " + url.getAuthority());
            out.println("getPath: " + url.getPath());
            out.println("getQuery: " + url.getQuery());
            out.println("getFile: " + url.getFile()); // getPath + getQuery
            out.println("getHost: " + url.getHost());
            out.println("getPort: " + url.getPort());
            out.println("getRef: " + url.getRef());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    static void HttpConnect(){
        try {
            URL url = new URL("http://www.google.com.tr/");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if(urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            }else {
                System.out.println("Please enter an HTTP URL.");
                return;
            }
            connection.setRequestMethod("GET");
            int response = connection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {

                BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = input.readLine()) != null) {
                    content.append(line);
                }
                input.close();

                out.println("Indirilen Veri:");
                out.println(content.toString());

            } else {
                out.println("Hata");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void HttpsConnect(){
        try {
            URL url = new URL("https://www.google.com.tr/");
            URLConnection urlConnection = url.openConnection();
            HttpsURLConnection connection = null;
            if(urlConnection instanceof HttpsURLConnection) {
                connection = (HttpsURLConnection) urlConnection;
            }else {
                System.out.println("Please enter an HTTP URL.");
                return;
            }
            connection.setRequestMethod("GET");
            int response = connection.getResponseCode();
            if (response == HttpsURLConnection.HTTP_OK) {

                BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = input.readLine()) != null) {
                    content.append(line);
                }
                input.close();

                out.println("Indirilen Veri:");
                out.println(content.toString());

            } else {
                out.println("Hata");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
//        UrlChecks();
//        HttpConnect();
        HttpsConnect();
    }
}
