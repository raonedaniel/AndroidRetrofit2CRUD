package com.cursoandroid.raone.androidretrofit2crud.remote;

public class APIUtils {

    private APIUtils(){

    }

    public static final String API_URL = "http://192.168.20.110/demo/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}
