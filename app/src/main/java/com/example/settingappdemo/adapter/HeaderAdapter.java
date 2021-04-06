package com.example.settingappdemo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settingappdemo.databinding.LayoutHeaderItemBinding;
import com.example.settingappdemo.databinding.LayoutSubHeaderBinding;
import com.example.settingappdemo.model.Client;

import java.util.ArrayList;
import java.util.List;

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER_TYPE = 0;
    public static final int SUB_ITEM_TYPE = 1;

    private List<Client> clientList;
    private OnItemHeaderClickListener listener;
    private Boolean isExpanded = false;

    public HeaderAdapter(List<Client> clientList) {
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            LayoutHeaderItemBinding binding = LayoutHeaderItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new HeaderViewHolder(binding);
        } else {
            LayoutSubHeaderBinding binding = LayoutSubHeaderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new HeaderSubViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).bind(clientList, listener, isExpanded);
        } else {
            ((HeaderSubViewHolder) holder).bind(clientList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else {
            return SUB_ITEM_TYPE;
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private LayoutHeaderItemBinding binding;

        public HeaderViewHolder(LayoutHeaderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(List<Client> clientList, OnItemHeaderClickListener listener, boolean isExpanded) {
            Client client = clientList.get(0);
            binding.ctAvatar.setCharacterName(client.getFirstNameCharacter());
            binding.ctAvatar.setBackgroundAvatar();
            binding.ctAvatar.setIsChecked();
            binding.tvName.setText(client.getName());
            binding.vTouch.setOnClickListener(view -> {
                listener.onItemHeaderClick();
            });
            binding.executePendingBindings();
        }
    }

    public static class HeaderSubViewHolder extends RecyclerView.ViewHolder {
        private LayoutSubHeaderBinding binding;

        public HeaderSubViewHolder(LayoutSubHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Client client) {
            binding.ctAvatar.setCharacterName(client.getFirstNameCharacter());
            binding.tvName.setText(client.getName());
            binding.ctAvatar.setBackgroundAvatar();
        }
    }

    public void setOnItemHeaderClickListener(OnItemHeaderClickListener listener) {
        this.listener = listener;
    }

    public void setClientList(List<Client> clientList) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ClientListDiffUtilCallBack(this.clientList, clientList));
        result.dispatchUpdatesTo(this);
        this.clientList = clientList;
    }

    public void setIsExpanded(Boolean isExpanded){
        this.isExpanded = isExpanded;
    }
}

class ClientListDiffUtilCallBack extends DiffUtil.Callback {

    List<Client> oldClients;
    List<Client> newClients;

    public ClientListDiffUtilCallBack(List<Client> oldClients, List<Client> newClients) {
        this.oldClients = oldClients;
        this.newClients = newClients;
    }

    @Override
    public int getOldListSize() {
        return oldClients.size();
    }

    @Override
    public int getNewListSize() {
        return newClients.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldClients.get(oldItemPosition).getId().equals(newClients.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldClients.get(oldItemPosition).equals(newClients.get(newItemPosition));
    }
}

