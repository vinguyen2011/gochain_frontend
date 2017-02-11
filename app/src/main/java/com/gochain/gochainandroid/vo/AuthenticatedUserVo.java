package com.gochain.gochainandroid.vo;

/**
 * Created by Brad on 11/02/2017.
 */

public class AuthenticatedUserVo {

    private Boolean authenticated;
    private String message;
    private String token;
    private UserVo userVo;

    public AuthenticatedUserVo() {
    }

    public AuthenticatedUserVo(Boolean authenticated, String message, String token, UserVo userVo) {
        this.authenticated = authenticated;
        this.message = message;
        this.token = token;
        this.userVo = userVo;
    }

    @Override
    public String toString() {
        return "AuthenticatedUserVo{" +
                "authenticated=" + authenticated +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", userVo=" + userVo +
                '}';
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}
