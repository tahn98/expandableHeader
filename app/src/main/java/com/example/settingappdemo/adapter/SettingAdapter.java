package com.example.settingappdemo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settingappdemo.databinding.LayoutSettingItemBinding;
import com.example.settingappdemo.databinding.LayoutSettingLineBinding;
import com.example.settingappdemo.databinding.LayoutSettingTitleBinding;
import com.example.settingappdemo.model.Setting;

import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int SETTING_ITEM_TYPE = 0;
    public static final int SETTING_TITLE_TYPE = 1;
    public static final int SETTING_LINE_TYPE = 2;
    private final List<Setting> settingData;

    public SettingAdapter(List<Setting> settingData) {
        this.settingData = settingData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SETTING_ITEM_TYPE) {
            LayoutSettingItemBinding binding = LayoutSettingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new SettingItemViewHolder(binding);
        } else if (viewType == SETTING_LINE_TYPE) {
            LayoutSettingLineBinding binding = LayoutSettingLineBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new SettingLineViewHolder(binding);
        } else {
            LayoutSettingTitleBinding binding = LayoutSettingTitleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new SettingTitleViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Setting item = settingData.get(position);
        if (item.getType() == SETTING_ITEM_TYPE) {
            ((SettingItemViewHolder) holder).bind(item);
        }
        if (item.getType() == SETTING_TITLE_TYPE) {
            ((SettingTitleViewHolder) holder).bind(item);
        }
    }

    @Override
    public int getItemCount() {
        return settingData.size();
    }

    @Override
    public int getItemViewType(int position) {
        Setting item = settingData.get(position);
        if (item.getType() == SETTING_ITEM_TYPE) {
            return SETTING_ITEM_TYPE;
        } else if (item.getType() == SETTING_LINE_TYPE) {
            return SETTING_LINE_TYPE;
        } else {
            return SETTING_TITLE_TYPE;
        }
    }

    public static class SettingItemViewHolder extends RecyclerView.ViewHolder {

        private LayoutSettingItemBinding binding;

        public SettingItemViewHolder(LayoutSettingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Setting setting) {
            this.binding.setSetting(setting);
            binding.executePendingBindings();
        }
    }

    public static class SettingTitleViewHolder extends RecyclerView.ViewHolder {

        private LayoutSettingTitleBinding binding;

        public SettingTitleViewHolder(LayoutSettingTitleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Setting setting) {
            this.binding.setSetting(setting);
            binding.executePendingBindings();
        }
    }

    public static class SettingLineViewHolder extends RecyclerView.ViewHolder {

        public SettingLineViewHolder(LayoutSettingLineBinding binding) {
            super(binding.getRoot());
        }
    }
}

