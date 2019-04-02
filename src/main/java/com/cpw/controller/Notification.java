package com.cpw.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.simple.JSONObject;

public class Notification {
	
//	final static private String FCM_URL = "https://fcm.googleapis.com/fcm/send";

	/**
	 * 
	 * 
	 * 
	 * Method to send push notification to Android FireBased Cloud messaging
	 * Server.
	 * 
	 * 
	 * 
	 * @param tokenId
	 *            Generated and provided from Android Client Developer
	 * 
	 * @param server_key
	 *            Key which is Generated in FCM Server
	 * 
	 * @param message
	 *            which contains actual information.
	 * @throws Exception 
	 * 
	 * 
	 * 
	 */
	/*public static void main(String[] args) throws Exception {
		String server_key = "AAAAiMmhW20:APA91bHuZ-XO3agCZr-YYyMJgNPuLKiNU6ZkofaOtCO32Vs7w5FzrmrDjK35dfmZhqhH-Bce4o3bukj5bF6h5XKS3ts5zb9OLI_kiIuJR22ods9UYsul0bR0VixjVTfJk3Pd9W1It-YU";
		send_FCM_Notification("eAnxY2VSfg4:APA91bHHNji1biEdoXcImxKrk30jVi2prThU6oFBoTrKI5VA-7DpA1tGc49mtfNDLrEcOJGGpFQJluMe3JXMmj_2P8T3II3B5CIbd7EVzgP11HroGXQZ0UJXpIC1enQElNa6igshpujS", server_key, "Hello");
	}*/
	//static String tokenId=null;
   /*  public void sendNotification() throws Exception{
    	 String server_key = "AAAAiMmhW20:APA91bHuZ-XO3agCZr-YYyMJgNPuLKiNU6ZkofaOtCO32Vs7w5FzrmrDjK35dfmZhqhH-Bce4o3bukj5bF6h5XKS3ts5zb9OLI_kiIuJR22ods9UYsul0bR0VixjVTfJk3Pd9W1It-YU";
         //send_FCM_Notification(tokenId, server_key, "Fcm Notification");
     }
	*/
	
	public void send_FCM_Notification(String tokenId, String server_key, String message,long id)throws Exception
	{

		try {

			// Create URL instance

			URL url = new URL("https://fcm.googleapis.com/fcm/send");

			// create connection.

			HttpURLConnection conn;

			conn = (HttpURLConnection) url.openConnection();

			conn.setUseCaches(false);

			conn.setDoInput(true);

			conn.setDoOutput(true);

			// set method as POST or GET

			conn.setRequestMethod("POST");

			// pass FCM server key

			conn.setRequestProperty("Authorization", "key=" + server_key);

			// Specify Message Format

			conn.setRequestProperty("Content-Type", "application/json");

			// Create JSON Object & pass value

			 JSONObject data =new JSONObject();
			 data.put("message",message);
			 data.put("id", id);
			 data.put("title", "Here is your notification.");

			 
			JSONObject infoJson = new JSONObject();

			
			
			infoJson.put("body", data);

			JSONObject json = new JSONObject();

			json.put("to", tokenId.trim());

//			json.put("notification", infoJson);
			json.put("notification", infoJson);
			
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

			wr.write(json.toString());

			wr.flush();
;
			int status = 0;

			if (null != conn) {

				status = conn.getResponseCode();

			}

			if (status != 0) {

				if (status == 200) {

					// SUCCESS message

					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					System.out.println("Android Notification Response : " + reader.readLine());

				} else if (status == 401) {

					// client side error

					System.out.println("Notification Response : TokenId : " + tokenId + " Error occurred :");

				} else if (status == 501) {

					// server side error

					System.out.println("Notification Response : [ errorCode=ServerError ] TokenId : " + tokenId);

				} else if (status == 503) {

					// server side error

					System.out.println("Notification Response : FCM Service is Unavailable  TokenId : " + tokenId);

				}

			}

		} catch (MalformedURLException mlfexception) {

			// Prototcal Error
			System.out.println("Error occurred while sending push Notification!.." + mlfexception.getMessage());
		} catch (IOException mlfexception) {

			// URL problem
			System.out.println(
					"Reading URL, Error occurred while sending push Notification!.." + mlfexception.getMessage());
		} catch (JSONException jsonexception) {
			// Message format error

			System.out.println(
					"Message Format, Error occurred while sending push Notification!.." + jsonexception.getMessage());

		} catch (Exception exception) {

			// General Error or exception.

			System.out.println("Error occurred while sending push Notification!.." + exception.getMessage());

		}

	}

		}
	
