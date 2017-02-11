package com.gochain.gochainandroid.vo;

/**
 * Created by Brad on 11/02/2017.
 */

public class AuthenticatedUserVo {

    private Boolean authenticated;
    private String message;
    private String token;
    private UserVo user;

    public AuthenticatedUserVo() {
    }

    public AuthenticatedUserVo(Boolean authenticated, String message, String token, UserVo user) {
        this.authenticated = authenticated;
        this.message = message;
        this.token = token;
        this.user = user;
    }

    @Override
    public String toString() {
        return "AuthenticatedUserVo{" +
                "authenticated=" + authenticated +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", user=" + user +
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

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }
}
