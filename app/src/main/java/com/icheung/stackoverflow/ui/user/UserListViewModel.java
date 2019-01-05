package com.icheung.stackoverflow.ui.user;

import com.icheung.stackoverflow.model.User;
import com.icheung.stackoverflow.repository.UsersRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class UserListViewModel extends ViewModel {
    private LiveData<List<User>> users;
    private UsersRepository usersRepository;

    @Inject
    public UserListViewModel(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void init() {
        if(users == null) users = usersRepository.getUsers();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }
}