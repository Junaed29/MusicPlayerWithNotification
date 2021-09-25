package com.jpsoft.onlinemusicplayer.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ApiClient {

    //private static String url = "http://192.168.0.100/GPService/";
    private static final int VOLLEY_TIMEOUT_MS = 30000;
    public static String HeaderKey = "Authorization";
    public static String SessionKey = "Session";
    private OnApiCallbackEventListener mOnApiCallbackEventListener;

    // Gets StringObject
    public static void get(final Context context, final String requestURL, final boolean useHeader, final OnApiCallbackEventListener onApiCallbackEventListener) {

        Log.d("ApiClient", "url: " + requestURL);

        if (isInternetConnected(context, true)) {

            StringRequest stringRequest = new StringRequest(Request.Method.GET, requestURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("ApiClient", "response: " + response);
                            onApiCallbackEventListener.onSuccess(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ApiClient", "error: " + error.getLocalizedMessage());
                    onApiCallbackEventListener.onFailure(error.getLocalizedMessage());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    if (useHeader) {
                        try {
                            //params.put(HeaderKey, "bearer " + new AppPrefs(context).getAccessToken());
                        } catch (Exception ex) {
                            //TODO: catch
                        }
                    }
                    return params;
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    VOLLEY_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        } else {
            onApiCallbackEventListener.onFailure("No internet");
        }

    }

    // Posts StringObject
    public static void post(final Context context, final String requestURL, final Map<String, String> parameters, final boolean useHeader, final OnApiCallbackEventListener onApiCallbackEventListener) {

        Log.d("ApiClient", "url: " + requestURL);

        if (isInternetConnected(context, true)) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, requestURL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("ApiClient", "response: " + response);
                            onApiCallbackEventListener.onSuccess(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("ApiClient", "error: " + error.getLocalizedMessage());
                            try {
                                if (error.networkResponse.data != null) {
                                    String body = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                                    JSONObject jsonObject = new JSONObject(body);
                                    String errorMsg = jsonObject.getString("error");
                                    onApiCallbackEventListener.onFailure(errorMsg);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //onApiCallbackEventListener.onFailure(error.getLocalizedMessage());
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    return parameters;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    if (useHeader) {
                        try {
                            //params.put(HeaderKey, "bearer " + new AppPrefs(context).getAccessToken());
                        } catch (Exception ex) {
                            //TODO: catch
                        }
                    }
                    return params;
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    VOLLEY_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        } else {
            onApiCallbackEventListener.onFailure("No internet");
        }

    }

    public static boolean isInternetConnected(Context context, boolean isNoInternetToast) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            if (isNoInternetToast) {
                Toast.makeText(context, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    public void setNewApiCallbackEventListener(OnApiCallbackEventListener eventListener) {
        mOnApiCallbackEventListener = eventListener;
    }

    public void onSuccess(String response) {
        if (mOnApiCallbackEventListener != null) {
            mOnApiCallbackEventListener.onSuccess(response);
        }
    }

    public void onFailure(String error) {
        if (mOnApiCallbackEventListener != null) {
            mOnApiCallbackEventListener.onFailure(error);
        }
    }

    public interface OnApiCallbackEventListener {
        void onSuccess(String response);

        void onFailure(String error);
    }
}