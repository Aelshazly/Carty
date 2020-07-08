package com.navibees.navigatorapp.network;

import com.navibees.navigatorapp.models.WeatherDetails;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApi {
    public static final String APPID = "dfc65c0711a1d1d256bfff0ba1007470";
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String CITY = "Madina,KSA";
    public static final String UNITS = "metric";
    private static WeatherApi ourInstance;
    private static Retrofit retrofit = null;

    public static WeatherApi getInstance() {
        if (ourInstance == null) {
            ourInstance = new WeatherApi();
        }
        return ourInstance;
    }

    private WeatherApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Level.BODY);
        retrofit = new Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(interceptor).build()).build();
    }

    public void getWeatherDetails(final NetworkResponse<WeatherDetails> networkResponse, String lang) {
        APIInterface apiInterface = (APIInterface) retrofit.create(APIInterface.class);
        if (lang == null) {
            lang = "en";
        }
        apiInterface.getWeatherDetails(CITY, APPID, UNITS, lang).enqueue(new Callback<WeatherDetails>() {
            public void onResponse(Call<WeatherDetails> call, Response<WeatherDetails> response) {
                if (response.isSuccessful()) {
                    networkResponse.onResponse(response.body());
                } else {
                    networkResponse.onFailure(new Exception("Request Failed"));
                }
            }

            public void onFailure(Call<WeatherDetails> call, Throwable t) {
                networkResponse.onFailure(t);
            }
        });
    }
}
