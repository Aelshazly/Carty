package com.navibees.navigatorapp.models;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AgendaData {
    @SerializedName("Agenda")
    @Expose
    public List<Agenda> agenda = null;

    public String toString() {
        return new Gson().toJson((Object) this);
    }

    public static AgendaData fromString(String str) {
        return (AgendaData) new Gson().fromJson(str, AgendaData.class);
    }
}
