/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icheung.stackoverflow.dagger;

import android.app.Application;

import com.icheung.stackoverflow.BuildConfig;
import com.icheung.stackoverflow.api.StackOverflowApi;
import com.icheung.stackoverflow.room.StackOverflowDatabase;
import com.icheung.stackoverflow.room.UserDao;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton
    @Provides
    StackOverflowApi provideStackOverflowApi(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com/2.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(StackOverflowApi.class);
    }

    @Singleton
    @Provides
    OkHttpClient provideHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Timber.tag("OkHttp").d(message);
                }
            });
            builder.addInterceptor(logging);
        }

        return builder.build();
    }

    @Singleton
    @Provides
    StackOverflowDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app, StackOverflowDatabase.class,"so.db").build();
    }

    @Singleton
    @Provides
    UserDao provideUserDao(StackOverflowDatabase db) {
        return db.userDao();
    }
}