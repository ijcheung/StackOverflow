package com.icheung.stackoverflow.room;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.icheung.stackoverflow.model.BadgeCounts;

import java.lang.reflect.Type;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static BadgeCounts fromString(String json) {
        Type type = new TypeToken<BadgeCounts>(){}.getType();
        return new Gson().fromJson(json, type);
    }
    @TypeConverter
    public static String fromBadgeCounts(BadgeCounts badgeCounts) {
        return new Gson().toJson(badgeCounts);
    }
}
