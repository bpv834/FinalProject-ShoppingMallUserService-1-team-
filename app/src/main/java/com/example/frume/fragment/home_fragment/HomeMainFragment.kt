package com.example.frume.fragment.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.frume.R

class HomeMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_main, container, false)
    }

}

// 하위 프래그먼트들의 이름
enum class HomeSubFragmentName(var number:Int, var str:String){
    // 게시글 목록 화면
    HOME_LIST_FRAGMENT(1, "HomeListFragment"),

}
