package com.icheung.stackoverflow.dagger;

import com.icheung.stackoverflow.ui.user.UserListViewModel;
import com.icheung.stackoverflow.viewmodel.StackOverflowViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel.class)
    abstract ViewModel bindUserListViewModel(UserListViewModel userListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(StackOverflowViewModelFactory factory);
}