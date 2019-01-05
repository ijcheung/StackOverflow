package com.icheung.stackoverflow.room;

import com.icheung.stackoverflow.model.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class StackOverflowDatabase extends RoomDatabase {
    abstract public UserDao userDao();
}