//package com.example.settingappdemo.widget
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.databinding.DataBindingUtil
//import com.example.settingappdemo.R
//import com.example.settingappdemo.databinding.LayoutExpandableHeaderBinding
//import com.example.settingappdemo.databinding.LayoutSubHeaderBinding
//import com.example.settingappdemo.model.Client
//
//class ExpandableHeader(context: Context, attrs: AttributeSet?, defStyle: Int) :
//    ConstraintLayout(context, attrs, defStyle) {
//
//    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
//    constructor(context: Context) : this(context, null, 0)
//
//    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)
//    private var parentBinding: LayoutExpandableHeaderBinding =
//        DataBindingUtil.inflate(layoutInflater, R.layout.layout_expandable_header, this, true)
//    private var isExpanded = false
//
//    var clientList: List<Client>? = null
//        set(value){
//            if(field == value || value == null) return
//            field = value
//            addSubClientListView(value)
//        }
//
//    init {
//        parentBinding.parent = this
//        parentBinding.isExpanded = isExpanded
//        parent
//    }
//
//    private fun addSubClientListView(list: List<Client>? = listOf()) {
//        list?.forEach { client ->
//            val inflater = LayoutInflater.from(context)
//            val subBinding = DataBindingUtil.inflate<LayoutSubHeaderBinding>(
//                inflater,
//                R.layout.layout_sub_header,
//                this,
//                false
//            )
//
//            subBinding.client = client
//            parentBinding.llSubClientList.addView(subBinding.root)
//        }
//    }
//
//    fun onExpand(){
//        isExpanded = !isExpanded
//        parentBinding.isExpanded = isExpanded
//    }
//}