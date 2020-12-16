package com.example.najmidpi.helper;

import android.content.Context;

public class UserHelper {

    private static final String USER_NAME_KEY = "userName";
    private static final String PASSWORD_KEY  = "password";
    private static final String EMAIL_KEY     = "email";
    private static final String PHONE_KEY     = "phone";
    private static final String HAS_USER_KEY  = "hasUser";

    private static UserHelper USER_HELPER = null;

    private SPHelper spHelper;

    private UserHelper(Context context) {
        this.spHelper = SPHelper.getInstance(context);
    }

    public static UserHelper getInstance(Context context){
        if (USER_HELPER == null){
            USER_HELPER = new UserHelper(context);
        }
        return USER_HELPER;
    }

    public void setUsername(String username){
        spHelper.writeString(USER_NAME_KEY , username);
    }

    public String getUsername(){
        return spHelper.readString(USER_NAME_KEY, "");
    }

    public void setPassword(String password){
        spHelper.writeString(PASSWORD_KEY , password);
    }

    public String getPassword(){
        return spHelper.readString(PASSWORD_KEY, "");
    }

    public void setEmail(String email){
        spHelper.writeString(EMAIL_KEY , email);
    }

    public String getEmail(){
        return spHelper.readString(EMAIL_KEY, "");
    }

    public void setPhone(String phone){
        spHelper.writeString(PHONE_KEY , phone);
    }

    public String getPhone(){
        return spHelper.readString(PHONE_KEY, "");
    }

    public void setHasUser(boolean hasUser){
        spHelper.writeBoolean(HAS_USER_KEY , hasUser);
    }

    public boolean hasUser(){
        return spHelper.readBoolean(HAS_USER_KEY, false);
    }
}
