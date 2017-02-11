package com.gochain.gochainandroid;

/**
 * Created by Brad on 11/02/2017.
 */

public class SessionValueHelper {

    private static String SESSION_TOKEN;

    public static String getSessionToken() {
        return SESSION_TOKEN;
    }

    public static void setSessionToken(String sessionToken) {
        SESSION_TOKEN = sessionToken;
    }
}
