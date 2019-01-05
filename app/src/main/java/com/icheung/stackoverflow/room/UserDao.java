package com.icheung.stackoverflow.room;

import com.icheung.stackoverflow.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * from user WHERE userId = :id")
    User getUser(int id);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getUsers();

    @Delete
    void deleteUser(User user);
}
