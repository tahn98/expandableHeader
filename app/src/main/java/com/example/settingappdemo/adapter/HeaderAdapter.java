package com.example.settingappdemo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settingappdemo.databinding.LayoutHeaderItemBinding;
import com.example.settingappdemo.model.Client;

import java.util.List;

public class HeaderAdapter extends RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder> {

    private List<Client> clientList;

    public HeaderAdapter(List<Client> clientList){
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutHeaderItemBinding binding = LayoutHeaderItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HeaderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HeaderViewHolder holder, int position) {
        holder.bind(clientList);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder{
        private LayoutHeaderItemBinding binding;

        public HeaderViewHolder(LayoutHeaderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(List<Client> clientList){
            binding.expHeader.setClientList(clientList);
            binding.executePendingBindings();
        }
    }
}
