package com.example.frume.fragment.home_fragment.user_home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.frume.MainActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserHomeBinding

class UserHomeFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var fragmentUserHomeBinding: FragmentUserHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserHomeBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_user_home,container,false)
        mainActivity = activity as MainActivity
        return fragmentUserHomeBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_user_home_search, menu) // 메뉴 XML 파일을 팽창

        val searchItem = menu.findItem(R.id.menuItemUserHomeSearch) // 메뉴 아이템 참조
        val searchView = searchItem.actionView as SearchView // SearchView로 변환

        // SearchView 동작 설정
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색 버튼 (엔터 또는 Done 버튼) 눌렀을 때 동작
                Toast.makeText(requireContext(), "Search: $query", Toast.LENGTH_LONG).show()
                mainActivity.showMessageDialog("s","s","a"){}
                searchView.clearFocus() // 키보드 닫기
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 텍스트 입력 중 동작
                return false
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }



}