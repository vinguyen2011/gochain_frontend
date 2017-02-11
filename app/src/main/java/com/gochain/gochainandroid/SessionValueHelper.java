package com.gochain.gochainandroid;

import com.gochain.gochainandroid.vo.AuthenticatedUserVo;

/**
 * Created by Brad on 11/02/2017.
 */

public class SessionValueHelper {

    private static AuthenticatedUserVo SESSION_USER;

    public static AuthenticatedUserVo getSessionUser() {
        return SESSION_USER;
    }

    public static void setSessionUser(AuthenticatedUserVo sessionUser) {
        SESSION_USER = sessionUser;
    }
}
