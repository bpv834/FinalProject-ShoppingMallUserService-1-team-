package com.example.frume.fragment.user_fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.UserActivity
import com.example.frume.databinding.FragmentUserLoginBinding


class UserLoginFragment() : Fragment() {

    lateinit var userLoginBinding: FragmentUserLoginBinding
    lateinit var userActivity: UserActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userActivity = activity as UserActivity
        userLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_user_login,container,false)


        onClickLoginButton()
        onClickSignUp()
        onClickNonMember()


        return userLoginBinding.root
    }

    fun onClickLoginButton() {
        userLoginBinding.buttonUserLogin.setOnClickListener {
            val dataBundle= Bundle()

            // UserActivity를 실행하고 현재 Activity를 종료한다.
            val homeIntent = Intent(userActivity, HomeActivity::class.java)
            startActivity(homeIntent)
            userActivity.finish()
        }


    }

    // 회원 가입 화면으로 이동시키는 메서드
    fun onClickSignUp() {
        userLoginBinding.textViewUserLoginSignUpButton.setOnClickListener {

            userActivity.replaceFragment(UserActivity.UserFragmentName.USER_SIGN_UP_FRAGMENT,false,false,null)

        }
    }

    // 비회원 접속시 홈화면으로 이동시키는 메서드
    fun onClickNonMember(){
        userLoginBinding.buttonUserLoginNonMember.setOnClickListener {
            // UserActivity를 실행하고 현재 Activity를 종료한다.
            val homeIntent = Intent(userActivity, HomeActivity::class.java)
            startActivity(homeIntent)
            userActivity.finish()
        }
    }

}