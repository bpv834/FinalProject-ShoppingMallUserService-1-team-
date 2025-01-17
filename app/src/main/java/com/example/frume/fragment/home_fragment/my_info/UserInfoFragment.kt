package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.HomeActivity
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoBinding
import com.example.frume.fragment.BottomNavFragment
import com.example.frume.fragment.SubMainFragmentName
import com.example.frume.util.UserInfoType

class UserInfoFragment(val bottomNavFragment: BottomNavFragment) : Fragment() {

    lateinit var homeActivity: HomeActivity
    lateinit var fragmentUserInfoBinding: FragmentUserInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentUserInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)
        homeActivity = activity as HomeActivity
        // Inflate the layout for this fragment
        onClickOrderHistory()
        onClickDeliverySpotManagement()
        onClickUserInfoManagementOrLeave()
        return fragmentUserInfoBinding.root
    }



    fun onClickOrderHistory() {
        fragmentUserInfoBinding.textViewUserInfoOrderHistory.setOnClickListener {
            val dataBundle = Bundle()
            Log.d("test100","UserInfoType.USER_ORDER_HISTORY_FRAGMENT.number : ${UserInfoType.USER_ORDER_HISTORY_FRAGMENT.number}")
            dataBundle.putInt("UserInfoType", UserInfoType.USER_ORDER_HISTORY_FRAGMENT.number)
           bottomNavFragment.combinationFragment.replaceFragment(
                SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                true,
                true,
                dataBundle
            )
        }
    }
    fun onClickDeliverySpotManagement() {
        fragmentUserInfoBinding.textViewUserInfoShippingInfo.setOnClickListener {
            val dataBundle = Bundle()
            Log.d("test100","UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT.number : ${UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT}")

            dataBundle.putInt("UserInfoType", UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT.number)
            bottomNavFragment.combinationFragment.replaceFragment(
                SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                true,
                true,
                dataBundle
            )
        }

    }

    fun onClickUserInfoManagementOrLeave() {
        fragmentUserInfoBinding.textViewUserInfoAccountInfo.setOnClickListener {
            val dataBundle = Bundle()
            Log.d("test100","UserInfoType.USER_INFO_MANAGE_FRAGMENT.number : ${UserInfoType.USER_INFO_MANAGE_FRAGMENT.number}")

            dataBundle.putInt("UserInfoType", UserInfoType.USER_INFO_MANAGE_FRAGMENT.number)
            bottomNavFragment.combinationFragment.replaceFragment(
                SubMainFragmentName.USER_INFO_MAIN_FRAGMENT,
                true,
                true,
                dataBundle
            )
        }

    }


}