package com.icheung.stackoverflow.repository;

import com.icheung.stackoverflow.api.StackOverflowApi;
import com.icheung.stackoverflow.model.User;
import com.icheung.stackoverflow.model.Wrapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UsersRepository {
    private StackOverflowApi stackOverflowApi;

    @Inject
    public UsersRepository(StackOverflowApi stackOverflowApi) {
        this.stackOverflowApi = stackOverflowApi;
    }

    public LiveData<List<User>> getUsers() {
        final MutableLiveData<List<User>> users = new MutableLiveData<List<User>>();
        stackOverflowApi.getUsers().enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                users.setValue(response.body().getUsers());
            }

            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {

            }
        });
        return users;
    }
}
