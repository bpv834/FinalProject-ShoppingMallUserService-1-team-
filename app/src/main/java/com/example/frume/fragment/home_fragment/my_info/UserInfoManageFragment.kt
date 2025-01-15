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
import com.example.frume.databinding.FragmentUserInfoManageBinding
import com.example.frume.fragment_main.UserInfoMainFragment
import com.example.frume.fragment_main.UserInfoSubFragment

class UserInfoManageFragment(val userInfoMainFragment: UserInfoMainFragment) : Fragment() {

    lateinit var fragmentUserInfoManageBinding: FragmentUserInfoManageBinding
    lateinit var homeActivity: HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserInfoManageBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_info_manage,
            container,
            false
        )
        onClickButtonUserInfoModify()
        onClickButtonModifyPw()

        return fragmentUserInfoManageBinding.root
    }

    fun onClickButtonUserInfoModify() {
        fragmentUserInfoManageBinding.buttonUserInfoManageModifyUserInfo.setOnClickListener {
            userInfoMainFragment.replaceFragment(
                UserInfoSubFragment.USER_INFO_MODIFY_FRAGMENT,
                true,
                true,
                null
            )
        }
    }

    fun onClickButtonModifyPw() {
        fragmentUserInfoManageBinding.buttonUserInfoManageModifyPW.setOnClickListener {
            userInfoMainFragment.replaceFragment(UserInfoSubFragment.USER_PW_MODIFY_FRAGMENT,true,true,null)
        }
    }

}