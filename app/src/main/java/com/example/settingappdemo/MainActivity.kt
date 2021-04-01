package com.example.settingappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.settingappdemo.databinding.ActivityMainBinding
import com.example.settingappdemo.model.Client

class MainActivity : AppCompatActivity() {
    private val clientList: MutableList<Client> = arrayListOf()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mockData()

        binding.expHeader.clientList = clientList
    }

    private fun mockData(){
        clientList.add(Client("1", "Gia Đình"))
        clientList.add(Client("2", "Công ty TNHH MTV"))
        clientList.add(Client("3", "Công ty TNHH TMA"))
    }
}