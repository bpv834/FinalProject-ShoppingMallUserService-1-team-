package com.example.frume.fragment.screen_manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.frume.R
import com.example.frume.databinding.FragmentBottomNavBinding
import com.example.frume.fragment.home_fragment.my_info.UserInfoFragment
import com.example.frume.fragment.home_fragment.user_home.UserHomeFragment
import com.example.frume.fragment.user_fragment.category.UserCategoryFragment
import com.example.frume.fragment.user_fragment.user_cart.UserCartFragment


class BottomNavFragment : Fragment() {
    private var _binding: FragmentBottomNavBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBottomNavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavMain.selectedItemId = R.id.menu_home
        setLayout()
        setFragment()
    }

    private fun setLayout() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_bottom_nav, UserHomeFragment())
            .commit()
    }

    private fun setFragment() {
        binding.bottomNavMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_category -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container_bottom_nav, UserCategoryFragment())
                        .commit()
                    true
                }

                R.id.menu_home -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container_bottom_nav, UserHomeFragment())
                        .commit()
                    true
                }

                R.id.menu_cart -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container_bottom_nav, UserCartFragment())
                        .commit()
                    true
                }

                R.id.menu_profile -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.container_bottom_nav, UserInfoFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}