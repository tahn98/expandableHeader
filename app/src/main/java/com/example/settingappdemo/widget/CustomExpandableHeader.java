package com.example.settingappdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.settingappdemo.R;
import com.example.settingappdemo.model.Client;

import java.util.List;

public class CustomExpandableHeader extends ConstraintLayout {

    List<Client> listClient;
    private AppCompatTextView tvName;
    private AppCompatTextView tvCharacterName;
    private CustomAvatarUser ctAvatarUser;
    private LinearLayout llSubList;
    private AppCompatImageView imExpandIcon;
    private boolean isExpanded = false;

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

    public void setClientList(List<Client> listClient) {
        if(this.listClient != listClient){
            this.listClient = listClient;
            addSubClientListView(this.listClient);
        }
    }

    public void init() {
        View view = inflate(getContext(), R.layout.layout_expandable_header, this);
        tvName = view.findViewById(R.id.tvName);
        tvCharacterName = view.findViewById(R.id.tvCharacterName);
        llSubList = view.findViewById(R.id.llSubClientList);
        imExpandIcon = view.findViewById(R.id.imExpandable);
        ctAvatarUser = view.findViewById(R.id.ctAvatar);

        imExpandIcon.setBackgroundResource(R.drawable.ic_icon_arrow_down);
        view.setOnClickListener(v -> toggle());
    }

    private void addSubClientListView(List<Client> clientList) {
        tvName.setText(listClient.get(0).getName());
        ctAvatarUser.setCharacterName(listClient.get(0).getFirstNameCharacter());
        ctAvatarUser.setBackgroundAvatar();
        ctAvatarUser.setIsChecked();

        List<Client> subClient = clientList.subList(1, clientList.size());

        for (Client client : subClient) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_sub_header, this, false);
            ((AppCompatTextView) view.findViewById(R.id.tvName)).setText(client.getName());
            ((CustomAvatarUser) view.findViewById(R.id.ctAvatar)).setCharacterName(client.getFirstNameCharacter());
            ((CustomAvatarUser) view.findViewById(R.id.ctAvatar)).setBackgroundAvatar();

            llSubList.addView(view);
        }
    }

    public void toggle() {
        isExpanded = !isExpanded;
        llSubList.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        imExpandIcon.setBackgroundResource(isExpanded ? R.drawable.ic_icon_arrow_up : R.drawable.ic_icon_arrow_down);
    }
}
