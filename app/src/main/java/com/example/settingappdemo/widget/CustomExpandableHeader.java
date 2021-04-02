package com.example.settingappdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.example.settingappdemo.R;
import com.example.settingappdemo.databinding.LayoutExpandableHeaderBinding;
import com.example.settingappdemo.databinding.LayoutSubHeaderBinding;
import com.example.settingappdemo.model.Client;

import java.util.List;

public class CustomExpandableHeader extends ConstraintLayout {
    public CustomExpandableHeader(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomExpandableHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomExpandableHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private LayoutExpandableHeaderBinding parentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(getContext()),
            R.layout.layout_expandable_header,
            this,
            true
    );

    private boolean isExpanded = false;

    List<Client> listClient;

    public void setClientList(List<Client> listClient){
        this.listClient = listClient;
        addSubClientListView(this.listClient);
    }

    public void init(){
        parentBinding.setParent(this);
        parentBinding.setIsExpanded(isExpanded);
    }

    private void addSubClientListView(List<Client> clientList){
        parentBinding.setClient(this.listClient.get(0));
        List<Client> subClient = clientList.subList(1, clientList.size());

        for(Client client: subClient){
            LayoutSubHeaderBinding subBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(getContext()),
                    R.layout.layout_sub_header,
                    this,
                    false
            );

            subBinding.setClient(client);
            parentBinding.llSubClientList.addView(subBinding.getRoot());
        }
    }

    public void onExpand(){
        isExpanded = !isExpanded;
        parentBinding.setIsExpanded(isExpanded);
    }
}
