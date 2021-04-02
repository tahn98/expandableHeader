package com.example.settingappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.MergeAdapter
import com.example.settingappdemo.adapter.HeaderAdapter
import com.example.settingappdemo.adapter.SettingAdapter
import com.example.settingappdemo.adapter.SettingAdapter.*
import com.example.settingappdemo.databinding.ActivityMainBinding
import com.example.settingappdemo.model.Client
import com.example.settingappdemo.model.Setting

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mergeSettingAdapter: MergeAdapter
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var settingAdapter: SettingAdapter

    private val clientList: MutableList<Client> = mutableListOf(
        Client("1", "Gia Dinh"),
        Client("2", "Công ty TNHH MTV"),
        Client("3", "Công ty TNHH TMA")
    )

    private val settingList: MutableList<Setting> = mutableListOf(
        Setting("st1", "Cài Đặt Chung", null, SETTING_TITLE_TYPE),
        Setting("st2", "Trang cá nhân", "Mọi thông tin giám sát và bảo mật", SETTING_ITEM_TYPE),
        Setting("st3", "Hộp Thư", "Kiểm tra mọi tin nhắn quan trọng", SETTING_ITEM_TYPE),
        Setting("st4", "", null, SETTING_LINE_TYPE)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        headerAdapter = HeaderAdapter(clientList)
        settingAdapter = SettingAdapter(settingList)
        mergeSettingAdapter = MergeAdapter(headerAdapter, settingAdapter)
        binding.rcvSetting.adapter = mergeSettingAdapter
    }
}