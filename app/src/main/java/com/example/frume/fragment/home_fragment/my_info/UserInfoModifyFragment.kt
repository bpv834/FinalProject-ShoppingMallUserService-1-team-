package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoModifyBinding
import com.example.frume.fragment_main.UserInfoMainFragment
import com.example.frume.fragment_main.UserInfoSubFragment

class UserInfoModifyFragment(val userInfoMainFragment: UserInfoMainFragment) : Fragment() {

    lateinit var fragmentUserInfoModifyBinding: FragmentUserInfoModifyBinding
    lateinit var homeActivity: HomeActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserInfoModifyBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_info_modify,
            container,
            false
        )
        onClickButtonSubmit()
        return fragmentUserInfoModifyBinding.root
    }

    fun onClickButtonSubmit() {
        fragmentUserInfoModifyBinding.buttonUserInfoManageModifyUserInfo.setOnClickListener {
            userInfoMainFragment.removeFragment(UserInfoSubFragment.USER_INFO_MODIFY_FRAGMENT)
        }
    }

}