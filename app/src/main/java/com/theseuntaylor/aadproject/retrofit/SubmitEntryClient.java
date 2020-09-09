package com.theseuntaylor.aadproject.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class SubmitEntryClient {
    private static int REQUEST_TIMEOUT = 60;
    private static String BASE_URL = "https://docs.google.com/forms/d/e/";
    private static Retrofit retrofit = null;
    private static ApiClient mInstance;
    private static OkHttpClient client;

    public static Retrofit getClient(){
        if (client == null) {
            initOkHTTP();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    private static void initOkHTTP(){
        OkHttpClient.Builder htBuilder = new OkHttpClient.Builder().connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS).writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        htBuilder.addInterceptor(interceptor);

        client = htBuilder.build();
    }
}
