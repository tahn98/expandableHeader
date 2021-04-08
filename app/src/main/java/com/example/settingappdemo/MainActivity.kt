package com.example.settingappdemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.MergeAdapter
import com.example.settingappdemo.adapter.HeaderAdapter
import com.example.settingappdemo.adapter.OnItemHeaderClickListener
import com.example.settingappdemo.adapter.SettingAdapter
import com.example.settingappdemo.adapter.SettingAdapter.*
import com.example.settingappdemo.databinding.ActivityMainBinding
import com.example.settingappdemo.model.Client
import com.example.settingappdemo.model.Setting
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mergeSettingAdapter: MergeAdapter
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var settingAdapter: SettingAdapter
    private var isExpanded: Boolean = false

    private val clientList: MutableList<Client> = mutableListOf(
        Client("1", "Gia Dinh", true),
        Client("2", "Công ty TNHH MTV"),
        Client("3", "Công ty TNHH TMA")
    )

    private val initClientList: MutableList<Client> = mutableListOf(
        Client("1", "Gia Dinh")
    )

    private val settingList: MutableList<Setting> = mutableListOf(
        Setting("st0", "", null, SETTING_LINE_TYPE),
        Setting("st1", "Cài Đặt Chung", null, SETTING_TITLE_TYPE),
        Setting("st2", "Trang cá nhân", "Mọi thông tin giám sát và bảo mật", SETTING_ITEM_TYPE),
        Setting("st3", "Hộp Thư", "Kiểm tra mọi tin nhắn quan trọng", SETTING_ITEM_TYPE),
        Setting("st4", "", null, SETTING_LINE_TYPE),
        Setting("st5", "Quản lý chung", null, SETTING_TITLE_TYPE),
        Setting(
            "st6",
            "Quản lý địa điểm",
            "Quản lý tất cả địa điểm của tôi cùng lúc",
            SETTING_ITEM_TYPE
        ),
        Setting(
            "st7",
            "Dịch vụ clound",
            "Dịch vụ clound để tăng cường giám sát",
            SETTING_ITEM_TYPE
        ),
        Setting("st8", "Danh sách mã", "Mã khả dụng của bạn", SETTING_ITEM_TYPE),
        Setting("st9", "Quản lý thiết bị kết nối", "Kết nối nhiều thiết bị hơn", SETTING_ITEM_TYPE),
        Setting("st10", "", null, SETTING_LINE_TYPE),
        Setting("st11", "Khác", null, SETTING_TITLE_TYPE),
        Setting(
            "st12",
            "Cài đặt ứng dụng",
            "Tùy chỉnh ứng dụng theo mong muốn của bạn",
            SETTING_ITEM_TYPE
        ),
        Setting("st13", "Hỗ trợ", "Hỏi và trả lời", SETTING_ITEM_TYPE),
        Setting("st14", "Về chúng tôi", "Hiểu thêm về chúng tôi", SETTING_ITEM_TYPE),
        Setting("st15", "Đăng xuất", "Bạn muốn đăng xuất tài khoản này?", SETTING_ITEM_TYPE),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        headerAdapter = HeaderAdapter(
            initClientList
        )
        headerAdapter.setOnItemHeaderClickListener(object : OnItemHeaderClickListener {
            override fun onItemHeaderClick(position: Int) {
                if(position == 0){
                    toggle()
                    val view = binding.rcvSetting.layoutManager?.findViewByPosition(0)
                    view?.findViewById<AppCompatImageView>(R.id.imExpandable)?.setImageResource(
                        if (isExpanded) R.drawable.ic_icon_arrow_up else R.drawable.ic_icon_arrow_down
                    )
                }
                else{
                    swapSubItemToHeaderItem(position)
                }
            }
        })

        settingAdapter = SettingAdapter(settingList)
        mergeSettingAdapter = MergeAdapter(headerAdapter, settingAdapter)
        binding.rcvSetting.adapter = mergeSettingAdapter
    }

    fun toggle() {
        if (isExpanded) {
            headerAdapter.setClientList(
                mutableListOf(
                    clientList.find { client -> client.isChecked }
                )
            )
        } else {
            headerAdapter.setClientList(
                clientList
            )
        }
        isExpanded = !isExpanded
        headerAdapter.setIsExpanded(isExpanded)
    }

    fun swapSubItemToHeaderItem(position: Int){
        for (i in clientList.indices) {
            clientList[i].isChecked = i == position
        }

        val indexClient = clientList.indexOfFirst { client -> client.isChecked }
        val client = clientList[indexClient]
        clientList.removeAt(indexClient)
        clientList.add(0, client)

        headerAdapter.setClientList(clientList)
        headerAdapter.notifyDataSetChanged()
    }
}