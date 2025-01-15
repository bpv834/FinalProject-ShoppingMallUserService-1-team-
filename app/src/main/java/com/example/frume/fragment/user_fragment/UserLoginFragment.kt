package com.example.frume.fragment.user_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.frume.R
import com.example.frume.databinding.FragmentCombinationBinding
import com.example.frume.databinding.FragmentUserLoginBinding
import com.example.frume.fragment.BottomNavFragment
import com.example.frume.fragment.FullScreenFragment
import com.example.frume.fragment.SubMainFragmentName


class UserLoginFragment(val fullScreenFragment: FullScreenFragment) : Fragment() {

    lateinit var userLoginBinding: FragmentUserLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_login,container,false)
        btn()
        return userLoginBinding.root
    }

    fun btn() {
        userLoginBinding.buttonUserLogin.setOnClickListener {
            val dataBundle= Bundle()

            dataBundle.putInt("home", 1)
            fullScreenFragment.combinationFragment.replaceFragment(SubMainFragmentName.NAV_MAIN_FRAGMENT, false, true, dataBundle)
        }
    }


}