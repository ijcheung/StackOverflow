package com.icheung.stackoverflow.viewmodel;

import com.icheung.stackoverflow.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserListViewModel extends ViewModel {
    private LiveData<List<User>> users;
}
