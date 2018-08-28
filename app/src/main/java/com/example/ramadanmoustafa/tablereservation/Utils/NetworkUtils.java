package com.example.ramadanmoustafa.tablereservation.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class NetworkUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
        }

    /**
     * Ignored in our example case study
     * Functionality to get authorization value for the header
     * <p><h>header-1</h>User-Agent: </p>
     * <p><h>header-2</h>Accepted-Language: en/ar</p>
     * <p><h>header-3</h>Date: as formatted like - Wed, 27 Apr 2016 02:37:40 GMT</p>
     * <p><h>header-4</h>Authorization: secretKey/SessionToken/user/pass </p>
     */
    public static Map<String, String> getAuthorizationHeaders(Context context) {
        Map<String, String> header = new LinkedHashMap<>();
        /*
        StringBuilder builder = new StringBuilder();
        builder.append(CHANNEL_NAME).append(";");
        builder.append(Security.getPlatformVersion()).append(";");
        builder.append(Security.getVersionNumber(context)).append(";");
        builder.append(Security.getDeviceName());
        String userAgent = builder.toString();
        header.put(WebConstants.USER_AGENT_KEY, userAgent);
        header.put(WebConstants.ACCEPTED_LANGUAGE_KEY, LocaleManager.getAppLocal().responseCode);
        String date = DateUtil.getCurrentUTCDateTime(Constant.HEADER_DATE_FORMAT, Locale.US) + " GMT";
        header.put(WebConstants.DATE_KEY, date);
        String signature = Security.hmacSHA1(date, WebConstants.SECRET_KEY);
        builder.setLength(0);
        builder.append(WebConstants.AUTH_IDENTIFICATION_KEY).append(" ");
        builder.append(WebConstants.CHANNEL_NAME).append(":");
        builder.append(signature);
        signature = builder.toString();
        header.put(WebConstants.AUTHORIZATION_KEY, signature);

        */
        return header;
    }

}
