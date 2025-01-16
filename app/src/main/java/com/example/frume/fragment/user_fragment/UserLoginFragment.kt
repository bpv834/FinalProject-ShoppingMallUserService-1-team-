package com.example.frume.fragment.user_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.frume.R
import com.example.frume.databinding.FragmentUserLoginBinding
import com.example.frume.fragment.screen_manager.BottomNavFragment


class UserLoginFragment : Fragment() {

    private var _binding: FragmentUserLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickLoginBtn()
    }

    private fun onClickLoginBtn() {
        binding.buttonUserLogin.setOnClickListener {
            setFragment(BottomNavFragment())
        }
    }

    private fun setFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.container_full_screen, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

}