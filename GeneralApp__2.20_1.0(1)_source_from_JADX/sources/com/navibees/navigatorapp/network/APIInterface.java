package com.navibees.navigatorapp.network;

import com.navibees.navigatorapp.models.AgendaData;
import com.navibees.navigatorapp.models.ResponseData;
import com.navibees.navigatorapp.models.WeatherDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("Agenda/Agenda.json")
    Call<AgendaData> getAgenda();

    @GET("Exhibitor/list.json")
    Call<ResponseData> getExhibitors();

    @GET("Speakers/list.json")
    Call<ResponseData> getSpeakers();

    @GET("Sponsors/list.json")
    Call<ResponseData> getSponsers();

    @GET("weather")
    Call<WeatherDetails> getWeatherDetails(@Query("q") String str, @Query("appid") String str2, @Query("units") String str3, @Query("lang") String str4);
}
