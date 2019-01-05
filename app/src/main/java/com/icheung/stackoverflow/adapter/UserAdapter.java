package com.icheung.stackoverflow.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.icheung.stackoverflow.AppExecutors;
import com.icheung.stackoverflow.R;
import com.icheung.stackoverflow.databinding.ItemUserBinding;
import com.icheung.stackoverflow.model.User;
import com.icheung.stackoverflow.room.UserDao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;

    private AppExecutors executors;
    private UserDao userDao;

    @Inject
    public UserAdapter(AppExecutors executors, UserDao userDao) {
        this.users = new ArrayList<>();

        this.executors = executors;
        this.userDao = userDao;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        final ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = users.get(position);
        final ItemUserBinding binding = holder.getBinding();

        binding.setUser(user);
        Glide.with(binding.avatar.getContext())
                .load(user.getProfileImage())
                .apply(new RequestOptions().placeholder(R.drawable.orbiting))
                .into(binding.avatar);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
            if (this.users == null) {
                this.users = users;
                notifyItemRangeInserted(0, users.size());
            } else {
                DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                    @Override
                    public int getOldListSize() {
                        return UserAdapter.this.users.size();
                    }

                    @Override
                    public int getNewListSize() {
                        return users.size();
                    }

                    @Override
                    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                        return UserAdapter.this.users.get(oldItemPosition).getUserId() ==
                                users.get(newItemPosition).getUserId();
                    }

                    @Override
                    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                        User newUser = users.get(newItemPosition);
                        User oldUser = UserAdapter.this.users.get(oldItemPosition);
                        return newUser.getUserId() == oldUser.getUserId()
                                && newUser.getDisplayName().equals(oldUser.getDisplayName())
                                && newUser.getProfileImage().equals(oldUser.getProfileImage())
                                && newUser.getReputation() == oldUser.getReputation()
                                && newUser.getBadgeCounts().getBronze() == oldUser.getBadgeCounts().getBronze()
                                && newUser.getBadgeCounts().getSilver() == oldUser.getBadgeCounts().getSilver()
                                && newUser.getBadgeCounts().getGold() == oldUser.getBadgeCounts().getGold();
                    }
                });
                this.users = users;
                result.dispatchUpdatesTo(this);
            }
        }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemUserBinding binding;

        public ViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public ItemUserBinding getBinding() {
            return binding;
        }
    }
}
