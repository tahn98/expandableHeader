package com.example.settingappdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.settingappdemo.databinding.LayoutHeaderItemBinding;
import com.example.settingappdemo.databinding.LayoutSubHeaderBinding;
import com.example.settingappdemo.model.Client;

import java.util.List;

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Client> clientList;
    private OnItemHeaderClickListener listener;
    private Boolean isExpanded = false;

    public HeaderAdapter(List<Client> clientList) {
        this.clientList = clientList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutHeaderItemBinding binding = LayoutHeaderItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HeaderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HeaderViewHolder) holder).bind(clientList, listener, isExpanded, position);
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private LayoutHeaderItemBinding binding;

        public HeaderViewHolder(LayoutHeaderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(List<Client> clientList, OnItemHeaderClickListener listener, boolean isExpanded, int position) {
            if(position == 0){
                Client client = clientList.get(0);
                binding.ctAvatar.setCharacterName(client.getFirstNameCharacter());
                binding.ctAvatar.setBackgroundAvatar();
                binding.ctAvatar.setIsChecked(true);
                binding.tvName.setText(client.getName());
                binding.vTouch.setOnClickListener(view -> {
                    listener.onItemHeaderClick();
                });
                binding.executePendingBindings();
            }else{
                Client client = clientList.get(position);
                binding.ctAvatar.setCharacterName(client.getFirstNameCharacter());
                binding.ctAvatar.setBackgroundAvatar();
                binding.tvName.setText(client.getName());
                binding.ctAvatar.setIsChecked(false);
                binding.imExpandable.setVisibility(View.GONE);
                ViewGroup.LayoutParams layoutParams = binding.ctAvatar.getLayoutParams();
                layoutParams.width = 80;
                layoutParams.height = 80;
                binding.ctAvatar.setLayoutParams(layoutParams);

                ConstraintLayout constraintLayout = binding.headerRoot;
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(binding.ctAvatar.getId(), ConstraintSet.START, binding.headerRoot.getId(), ConstraintSet.START, 34);
                constraintSet.applyTo(constraintLayout);

                binding.executePendingBindings();

            }
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

