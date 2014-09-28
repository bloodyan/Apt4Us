package com.shsoft.apt4us.fragments;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.shsoft.apt4us.APTFragmentActivity;
import com.shsoft.apt4us.BaseComponents.A4UDefines;

public class A4UFragment_APT_Alarm extends Fragment {
	
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "968155674700";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "SHRYU";

    TextView mDisplay;
    GoogleCloudMessaging gcm;
    AtomicInteger msgId = new AtomicInteger();
    SharedPreferences prefs;
    Context context;

	FragmentActivity mFA;
    String regid;

    
	public A4UFragment_APT_Alarm() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mFA = getActivity();
		context = mFA.getApplicationContext();
		
		if(checkPlayServices())
		{
			gcm = GoogleCloudMessaging.getInstance(mFA);
            regid = getRegistrationId(context);
            
            if (regid.isEmpty()) {
            	registerInBackground();
            }
            else
            {
            	//test code for requestPush
            	requestPush();
            }
		}
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkPlayServices();
	}
	private boolean checkPlayServices() {
		mFA = getActivity();
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mFA);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, mFA,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i("SHRYU", "This device is not supported.");
	            //finish();
	        }
	        return false;
	    }
	    return true;
	}
	
	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 *
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	private String getRegistrationId(Context context) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    if (registrationId.isEmpty()) {
	        Log.i(TAG, "Registration not found.");
	        return "";
	    }
	    // Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the fnew
	    // app version.
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
	    return registrationId;
	}
	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGCMPreferences(Context context) {
	    // This sample app persists the registration ID in shared preferences, but
	    // how you store the regID in your app is up to you.
	    return mFA.getSharedPreferences(APTFragmentActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	
	/**
	 * Registers the application with GCM servers asynchronously.
	 * <p>
	 * Stores the registration ID and app versionCode in the application's
	 * shared preferences.
	 */
	private void registerInBackground() {
	    new AsyncTask<String, Integer, Object>() {

	    	protected void onPostExecute(Object result)
	    	{
	    		Log.i("SHRYU", "on post execute" + result);
	    		//mDisplay.append(msg + "\n");
	    	};
	    	
			@Override
			protected Object doInBackground(String... params) {
				// TODO Auto-generated method stub
				 String msg = "";
		            try {
		                if (gcm == null) {
		                    gcm = GoogleCloudMessaging.getInstance(context);
		                }
		                regid = gcm.register(SENDER_ID);
		                msg = "Device registered, registration ID=" + regid;

		                // You should send the registration ID to your server over HTTP,
		                // so it can use GCM/HTTP or CCS to send messages to your app.
		                // The request to your server should be authenticated if your app
		                // is using accounts.
		                sendRegistrationIdToBackend();

		                // For this demo: we don't need to send it because the device
		                // will send upstream messages to a server that echo back the
		                // message using the 'from' address in the message.

		                // Persist the regID - no need to register again.
		                storeRegistrationId(context, regid);
		            } catch (IOException ex) {
		                msg = "Error :" + ex.getMessage();
		                // If there is an error, don't just keep trying to register.
		                // Require the user to click a button again, or perform
		                // exponential back-off.
		            }
		            return msg;
			}
	    }.execute(null, null, null);
	}
	
	private void sendRegistrationIdToBackend() {
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				HttpClient httpClient = new DefaultHttpClient();

				String urlString = A4UDefines.PushServerURL + "/requestPush/" + regid;
				try {
					URI url = new URI(urlString);

					HttpPost httpPost = new HttpPost();
					httpPost.setURI(url);

//					List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(
//							2);
//					nameValuePairs.add(new BasicNameValuePair("userId",
//							"saltfactory"));
//					nameValuePairs.add(new BasicNameValuePair("password",
//							"password"));
//
//					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

					HttpResponse response = httpClient.execute(httpPost);
					String responseString = EntityUtils.toString(
							response.getEntity(), HTTP.UTF_8);

					Log.d(TAG, responseString);

				} catch (URISyntaxException e) {
					Log.e(TAG, e.getLocalizedMessage());
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					Log.e(TAG, e.getLocalizedMessage());
					e.printStackTrace();
				} catch (IOException e) {
					Log.e(TAG, e.getLocalizedMessage());
					e.printStackTrace();
				}

			}
		};

		thread.start();
	}
	
	/**
	 * Stores the registration ID and app versionCode in the application's
	 * {@code SharedPreferences}.
	 *
	 * @param context application's context.
	 * @param regId registration ID
	 */
	private void storeRegistrationId(Context context, String regId) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    int appVersion = getAppVersion(context);
	    Log.i(TAG, "Saving regId on app version " + appVersion);
	    SharedPreferences.Editor editor = prefs.edit();
	    editor.putString(PROPERTY_REG_ID, regId);
	    editor.putInt(PROPERTY_APP_VERSION, appVersion);
	    editor.commit();
	}
	
	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	boolean requestPush()
	{
		boolean ret = false;
		if(!regid.isEmpty())
		{
			ret = true;

			Thread thread = new Thread() {
				@Override
				public void run() {
					HttpClient httpClient = new DefaultHttpClient();
					
					String urlString = A4UDefines.PushServerURL + "requestPush/" + regid;
				 try {
					 String sProtocol = urlString.split("://")[0];
					 URL url = new URL(urlString);		
					 if(urlString.split("://")[0].compareTo("https") == 0)
					 {
						 
						 
						 trustAllHosts();
						 HttpsURLConnection https = (HttpsURLConnection) url.openConnection(); 
						 https.setHostnameVerifier(DO_NOT_VERIFY); 
						 HttpURLConnection conn = null; 
						 conn = https; 
	            
						 if (conn != null) {
							 conn.setConnectTimeout(10000);
							 conn.setUseCaches(false);
							 int resultcode = 0;
							 conn.connect();
							 Log.d(TAG, "result : " + conn.getResponseCode());
						 }
					 }
                     //http 
                     if(urlString.split("://")[0].compareTo("https") == 0)
                     {
	                     HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	                     connection.setRequestMethod("POST");
	                     connection.setDoInput(true);
	                     connection.setDoOutput(true);
	                     connection.connect();
	
	                     StringBuilder responseStringBuilder = new StringBuilder();
	                     if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
	                    	 Log.d(TAG,"Connected");
	                     }
	
	                     connection.disconnect();
						
	                     Log.d(TAG, responseStringBuilder.toString());
                      }
                 } catch (MalformedURLException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }


			
				}
			};
			
			thread.start();

		}
		return ret;
	}

	
	private static void trustAllHosts() { 
	    // Create a trust manager that does not validate certificate chains 
	    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() { 
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() { 
	                    return new java.security.cert.X509Certificate[] {}; 
	            } 
	
	            @Override 
	            public void checkClientTrusted( 
	                    java.security.cert.X509Certificate[] chain, 
	                    String authType) 
	                    throws java.security.cert.CertificateException { 
	                // TODO Auto-generated method stub 
	                 
	            } 
	
	            @Override 
	            public void checkServerTrusted( 
	                    java.security.cert.X509Certificate[] chain, 
	                    String authType) 
	                    throws java.security.cert.CertificateException { 
	                // TODO Auto-generated method stub 
	                 
	            } 
	    } }; 
	
	    // Install the all-trusting trust manager 
	    try { 
	            SSLContext sc = SSLContext.getInstance("TLS"); 
	            sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
	            HttpsURLConnection 
	                            .setDefaultSSLSocketFactory(sc.getSocketFactory()); 
	    } catch (Exception e) { 
	            e.printStackTrace(); 
	    } 
	} 
 
	final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() { 
	    
		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			// TODO Auto-generated method stub
			return true;
		} 
	}; 

}
