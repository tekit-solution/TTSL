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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cpw.model.NotificationRequest;
import com.cpw.services.NotificationImpl;

@RestController
public class NotificationController {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "sendNotrification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> send_FCM_Notification(@RequestBody NotificationRequest request)throws Exception
	{
		logger.info("Entering into controller");
		NotificationImpl notificationImpl=new NotificationImpl();
	// int response=notificationImpl.insertDetails(request);
		
		final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
	  final	String server_key = "AAAAiMmhW20:APA91bHuZ-XO3agCZr-YYyMJgNPuLKiNU6ZkofaOtCO32Vs7w5FzrmrDjK35dfmZhqhH-Bce4o3bukj5bF6h5XKS3ts5zb9OLI_kiIuJR22ods9UYsul0bR0VixjVTfJk3Pd9W1It-YU";
		try {

			// Create URL instance

			URL url = new URL(FCM_URL);

			// create connection.

			HttpURLConnection conn;

			conn = (HttpURLConnection) url.openConnection();

			conn.setUseCaches(false);

			conn.setDoInput(true);

			conn.setDoOutput(true);

			// set method as POST or GET

			//conn.setRequestMethod("POST");

			// pass FCM server key

			conn.setRequestProperty("Authorization", "key=" + server_key);

			// Specify Message Format

			conn.setRequestProperty("Content-Type", "application/json");

			// Create JSON Object & pass value

			JSONObject infoJson = new JSONObject();

			infoJson.put("title", "Here is your notification.");

			
			infoJson.put("body", request.getMessage());

			JSONObject json = new JSONObject();

			json.put("to", request.getTokenId().trim());

			json.put("notification", infoJson);

			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

			wr.write(json.toString());
            
			wr.flush();

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

					System.out.println("Notification Response : TokenId : " + request.getTokenId() + " Error occurred :");

				} else if (status == 501) {

					// server side error

					System.out.println("Notification Response : [ errorCode=ServerError ] TokenId : " + request.getTokenId());

				} else if (status == 503) {

					// server side error

					System.out.println("Notification Response : FCM Service is Unavailable  TokenId : " + request.getTokenId());

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
		/*if(response>0)
		{
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}*/
		return null;

	}

}
