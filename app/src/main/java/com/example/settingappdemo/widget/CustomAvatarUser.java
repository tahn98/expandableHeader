package com.example.settingappdemo.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.settingappdemo.R;

import java.util.Random;

public class CustomAvatarUser extends ConstraintLayout {
    private AppCompatTextView tvCharacterName;
    private View background;
    private View imChecked;

    public CustomAvatarUser(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomAvatarUser(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomAvatarUser(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        View view = inflate(getContext(), R.layout.layout_avatar, this);
        tvCharacterName = view.findViewById(R.id.tvCharacterName);
        background = view.findViewById(R.id.imAvatar);
        imChecked = view.findViewById(R.id.imChecked);
    }

    public void setCharacterName(String characterName){
        tvCharacterName.setText(characterName);
    }

    public void setBackgroundAvatar(){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bg_circle_image_view);
        drawable.setColorFilter(new PorterDuffColorFilter(getRandomColor(), PorterDuff.Mode.MULTIPLY));

        background.setBackground(drawable);
    }

    public void setIsChecked(boolean checked){
        if(checked){
            imChecked.setVisibility(VISIBLE);
        }else{
            imChecked.setVisibility(GONE);
        }
    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
