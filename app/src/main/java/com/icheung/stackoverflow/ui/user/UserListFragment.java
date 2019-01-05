package com.icheung.stackoverflow.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icheung.stackoverflow.R;
import com.icheung.stackoverflow.adapter.UserAdapter;
import com.icheung.stackoverflow.dagger.Injectable;
import com.icheung.stackoverflow.databinding.FragmentUserListBinding;
import com.icheung.stackoverflow.model.User;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class UserListFragment extends Fragment implements Injectable {
    public static final String TAG = UserListFragment.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    UserAdapter adapter;

    private FragmentUserListBinding binding;

    private UserListViewModel viewModel;

    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list, container, false);

        binding.recycler.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel.class);
        viewModel.init();
        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> users) {
                if(users == null) {
                    binding.setIsLoading(true);
                } else {
                    binding.setIsLoading(false);
                    adapter.setUsers(users);
                }

                binding.executePendingBindings();
            }
        });
    }
}
