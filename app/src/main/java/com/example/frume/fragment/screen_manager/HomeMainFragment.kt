package com.example.frume.fragment.screen_manager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.frume.R
import com.example.frume.databinding.FragmentHomeMainBinding
import com.example.frume.fragment.user_fragment.UserLoginFragment

class HomeMainFragment : Fragment() {
    private var _binding: FragmentHomeMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }


    // 초기 프래그먼트는 로그인 프래그먼트
    private fun setLayout() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container_home_main, FullScreenFragment())
            .commit()
    }

    // 바꿔 줄 네비게이션 프래그먼트
    fun switchToNavFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_home_main, BottomNavFragment())
            .addToBackStack("BottomNavFragment")
            .commit()
    }

    // 바꿔 줄 프래그먼트
    fun switchToFragment(data: Bundle?) {
        val fragment = FullScreenFragment().apply {
            arguments = data // 전달받은 Bundle을 arguments로 설정
        }
        Log.d("HomeMainFragment", "Switching to FullScreenFragment with data: $data")
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_home_main, fragment)
            .commit()
    }
}
