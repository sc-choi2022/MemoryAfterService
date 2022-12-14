package com.example.memoryafterservice.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

//    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private static final String BASE_URL = "http://j7b103.p.ssafy.io:8080/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public MemberApi getMemberApi() {
        return retrofit.create(MemberApi.class);
    }

    public AuthApi getAuthApi() {
        return retrofit.create(AuthApi.class);
    }

    public FileApi getFileApi() { return retrofit.create(FileApi.class); }
}

