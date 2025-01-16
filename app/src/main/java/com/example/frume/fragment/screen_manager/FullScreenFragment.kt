package com.example.frume.fragment.screen_manager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.frume.R
import com.example.frume.databinding.FragmentFullScreenBinding
import com.example.frume.fragment.home_fragment.my_info.UserInfoManageFragment
import com.example.frume.fragment.home_fragment.my_info.UserInfoModifyFragment
import com.example.frume.fragment.user_fragment.UserLoginFragment
import com.example.frume.fragment.user_fragment.category.UserCategoryFragment


class FullScreenFragment : Fragment() {
    private var _binding: FragmentFullScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //   changeFragment(UserLoginFragment())
        // arguments에서 Fragment 이름 가져오기
        val fragmentName = arguments?.getString("Fragment")
        Log.d("FullScreenFragment", "Received fragmentName: $fragmentName")

        handleFragmentSwitch(fragmentName ?: "")
    }

    private fun changeFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_full_screen, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun handleFragmentSwitch(name: String) {
        Log.d("TEST", name)
        Log.d("TEST", name)
        when (name) {
            "User" -> {
                changeFragment(UserCategoryFragment())
            }

            "Info" -> {
                changeFragment(UserInfoManageFragment())
            }

            "InfoModify" -> {
                changeFragment(UserInfoModifyFragment())
            }

            else -> {
                changeFragment(UserLoginFragment())
            }
        }
    }
}