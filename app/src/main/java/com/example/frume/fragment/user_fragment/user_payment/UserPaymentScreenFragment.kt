package com.example.frume.fragment.user_fragment.user_payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserPaymentScreenBinding

class UserPaymentScreenFragment() : Fragment() {

    lateinit var fragmentUserPaymentScreenBinding: FragmentUserPaymentScreenBinding
    lateinit var homeActivity: HomeActivity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        fragmentUserPaymentScreenBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_payment_screen, container, false)
        homeActivity = activity as HomeActivity

        // 네비게이션 아이콘 클릭 리스너 설정
        fragmentUserPaymentScreenBinding.toolbarUserPaymentScreen.setNavigationOnClickListener {

        }

        // Inflate the layout for this fragment
        return fragmentUserPaymentScreenBinding.root
    }
}

