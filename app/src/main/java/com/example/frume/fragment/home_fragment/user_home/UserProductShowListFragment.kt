package com.example.frume.fragment.home_fragment.user_home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserProductShowListBinding


class UserProductShowListFragment() : Fragment() {

    lateinit var mainActivity: MainActivity
    lateinit var fragmentUserProductShowListBinding : FragmentUserProductShowListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentUserProductShowListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_product_show_list, container, false)


        return fragmentUserProductShowListBinding.root
    }





}