package com.example.frume.fragment.user_fragment.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserCategoryDetailBinding


class UserCategoryDetailFragment() : Fragment() {

    lateinit var homeActivity: HomeActivity
    lateinit var fragmentUserCategoryDetailBinding: FragmentUserCategoryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentUserCategoryDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_category_detail, container, false)

        homeActivity = activity as HomeActivity

        // 과일 카테고리 이름을 가져온다 (ProductMainFragment에서 dataBundle )
        val selectedFruit = arguments?.getString("selectedFruit") ?: "알 수 없는 과일"
        fragmentUserCategoryDetailBinding.toolbarUserCategoryDetail.title = "$selectedFruit"

        return fragmentUserCategoryDetailBinding.root
    }
}