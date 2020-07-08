package com.navibees.navigatorapp.network;

import com.navibees.navigatorapp.models.AgendaData;
import com.navibees.navigatorapp.models.Exhibitor;
import com.navibees.navigatorapp.models.ResponseData;
import com.navibees.navigatorapp.models.Speaker;
import com.navibees.navigatorapp.models.Sponsor;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    public static final String AGENDA = "Agenda/";
    public static final String AGENDA_JSON = "Agenda.json";
    public static final String BASE_URL = "http://cms2.navibees.com/Demo/";
    public static final String EVENT = "MasjidAlNabwi/";
    public static final String EXHIBITORS = "Exhibitor/";
    public static final String LIST_JSON = "list.json";
    public static final String SPEAKERS = "Speakers/";
    public static final String SPONSERS = "Sponsors/";
    private static RestApi ourInstance;
    private static Retrofit retrofit = null;

    public static RestApi getInstance() {
        if (ourInstance == null) {
            ourInstance = new RestApi();
        }
        return ourInstance;
    }

    private RestApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Level.BODY);
        retrofit = new Builder().baseUrl("http://cms2.navibees.com/Demo/MasjidAlNabwi/").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(interceptor).build()).build();
    }

    public void getExhibitors(final NetworkResponse<List<Exhibitor>> networkResponse) {
        ((APIInterface) retrofit.create(APIInterface.class)).getExhibitors().enqueue(new Callback<ResponseData>() {
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    networkResponse.onResponse(((ResponseData) response.body()).exhibitors);
                } else {
                    networkResponse.onFailure(new Exception("Request Failed"));
                }
            }

            public void onFailure(Call<ResponseData> call, Throwable t) {
                networkResponse.onFailure(t);
            }
        });
    }

    public void getSponsers(final NetworkResponse<List<Sponsor>> networkResponse) {
        ((APIInterface) retrofit.create(APIInterface.class)).getSponsers().enqueue(new Callback<ResponseData>() {
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    networkResponse.onResponse(((ResponseData) response.body()).sponsers);
                } else {
                    networkResponse.onFailure(new Exception("Request Failed"));
                }
            }

            public void onFailure(Call<ResponseData> call, Throwable t) {
                networkResponse.onFailure(t);
            }
        });
    }

    public void getSpeakers(final NetworkResponse<List<Speaker>> networkResponse) {
        ((APIInterface) retrofit.create(APIInterface.class)).getSpeakers().enqueue(new Callback<ResponseData>() {
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    networkResponse.onResponse(((ResponseData) response.body()).speakers);
                } else {
                    networkResponse.onFailure(new Exception("Request Failed"));
                }
            }

            public void onFailure(Call<ResponseData> call, Throwable t) {
                networkResponse.onFailure(t);
            }
        });
    }

    public void getAgendaData(final NetworkResponse<AgendaData> networkResponse) {
        ((APIInterface) retrofit.create(APIInterface.class)).getAgenda().enqueue(new Callback<AgendaData>() {
            public void onResponse(Call<AgendaData> call, Response<AgendaData> response) {
                if (response.isSuccessful()) {
                    networkResponse.onResponse(response.body());
                } else {
                    networkResponse.onFailure(new Exception("Request Failed"));
                }
            }

            public void onFailure(Call<AgendaData> call, Throwable t) {
                networkResponse.onFailure(t);
            }
        });
    }
}
