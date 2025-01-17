package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.data_ye.DummyData
import com.example.frume.databinding.FragmentUserAddressManageBinding
import com.example.frume.databinding.ItemDeliverySpotBinding
import com.example.frume.fragment.CombinationFragment
import com.example.frume.fragment.home_fragment.user_home.UserHomeFragment
import com.example.frume.fragment.user_fragment.user_cart.UserCartFragment
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.example.frume.fragment_main.UserInfoMainFragment

class UserAddressManageFragment(userInfoMainFragment: UserInfoMainFragment) : Fragment() {

    lateinit var fragmentUserAddressManageBinding : FragmentUserAddressManageBinding
    lateinit var homeActivity: HomeActivity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserAddressManageBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_address_manage, container, false)
        homeActivity = activity as HomeActivity

        // RecyclerView 설정
        settingRecyclerViewUserAddressManage()

        // Inflate the layout for this fragment
        return fragmentUserAddressManageBinding.root
    }


    // RecyclerView를 구성하는 메서드
    fun settingRecyclerViewUserAddressManage() {
        fragmentUserAddressManageBinding.apply {
            // 더미 데이터를 이용하여 어댑터에 전달
            recyclerViewUserOrderHistory.adapter = RecyclerViewUserAddressManageAdapter(DummyData.getDummyUserAddresses())
            recyclerViewUserOrderHistory.layoutManager = LinearLayoutManager(homeActivity)
            val deco = MaterialDividerItemDecoration(homeActivity, MaterialDividerItemDecoration.VERTICAL)
            recyclerViewUserOrderHistory.addItemDecoration(deco)
        }
    }


    // RecyclerView의 어댑터
    inner class RecyclerViewUserAddressManageAdapter(private val addressList: List<DummyData.UserAddress>) : RecyclerView.Adapter<RecyclerViewUserAddressManageAdapter.ViewHolderUserAddress>() {

        // ViewHolder
        inner class ViewHolderUserAddress(val binding: ItemDeliverySpotBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUserAddress {
            val binding = ItemDeliverySpotBinding.inflate(layoutInflater, parent, false)
            return ViewHolderUserAddress(binding)
        }

        override fun getItemCount(): Int {
            return addressList.size
        }

        override fun onBindViewHolder(holder: ViewHolderUserAddress, position: Int) {
            val item = addressList[position]

            // 주소 이름, 상세 주소, 우편번호, 아이콘 리소스 바인딩
            holder.binding.apply {
                addressName.text = item.addressName
                addressDetail.text = item.addressDetail
                postalCode.text = item.postalCode
                addressIcon.setImageResource(item.addressIconResId)
            }
        }

    }
}

