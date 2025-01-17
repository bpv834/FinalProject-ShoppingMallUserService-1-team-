package com.example.frume.fragment.user_fragment.user_cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.data_ye.DummyData
import com.example.frume.databinding.FragmentUserCart1Binding
import com.example.frume.databinding.ItemUsercartListBinding
import com.example.frume.fragment.home_fragment.my_info.UserAddressManageFragment
import com.example.frume.fragment.user_fragment.user_payment.UserPaymentScreenFragment
import com.example.frume.fragment_main.PaymentMainFragment
import com.example.frume.util.UserInfoType
import com.example.frume.util.UserPaymentType
import com.google.android.material.divider.MaterialDividerItemDecoration

class UserCartFragment1(val userCartFragment: UserCartFragment) : Fragment() {

    lateinit var fragmentUserCart1Binding: FragmentUserCart1Binding
    lateinit var homeActivity: HomeActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUserCart1Binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_cart1, container, false)
        homeActivity = activity as HomeActivity

       /* // 배송지 변경 버튼 호출
        onClickCartDeliverySpotChange()*/

        // 구매하기 버튼 호출
        onClickCartOrderProduct()

        // RecyclerView 초기화
        settingRecyclerViewUserCartProduct()

        // Inflate the layout for this fragment
        return fragmentUserCart1Binding.root
    }

/*
    // 배송지 변경 버튼 클릭 시, UserAddressManageFragment로 이동
    fun onClickCartDeliverySpotChange() {
        fragmentUserCart1Binding.buttonUserCartDialogModifyAddress.setOnClickListener {
            // UserAddressManageFragment로 이동
            val fragment = UserAddressManageFragment()
            // 필요 시 데이터 전달
            val bundle = Bundle()
            bundle.putInt("userInfoType", UserInfoType.USER_ADDRESS_MANAGE_FRAGMENT.number)
            fragment.arguments = bundle
            navigateToFragment(fragment)
        }
    }
*/

    // 구매하기 버튼 클릭 시, UserPaymentScreenFragment로 이동
    fun onClickCartOrderProduct() {
        fragmentUserCart1Binding.buttonUserCartOrder.setOnClickListener {
            // UserPaymentScreenFragment로 이동
            val fragment = UserPaymentScreenFragment()
            // 필요 시 데이터 전달
            val bundle = Bundle()
            bundle.putInt("userPaymentType", UserPaymentType.USER_CART_FRAGMENT1.number)
            fragment.arguments = bundle
            navigateToFragment(fragment)
        }
    }

    // 프래그먼트 이동을 처리하는 함수
    private fun navigateToFragment(fragment: Fragment) {
        homeActivity.supportFragmentManager.beginTransaction().replace(R.id.containerCombination, fragment).commit()
    }

    // RecyclerView를 구성하는 메서드
    fun settingRecyclerViewUserCartProduct() {
        fragmentUserCart1Binding.apply {
            // 더미 데이터를 이용하여 어댑터에 전달
            recyclerViewUserCart1.adapter = RecyclerViewUserCartProductAdapter(DummyData.getDummyCartItems())
            recyclerViewUserCart1.layoutManager = LinearLayoutManager(homeActivity)
            val deco = MaterialDividerItemDecoration(homeActivity, MaterialDividerItemDecoration.VERTICAL)
            recyclerViewUserCart1.addItemDecoration(deco)
        }
    }

    // RecyclerView의 어댑터
    inner class RecyclerViewUserCartProductAdapter(private val cartItems: List<DummyData.CartItem>) : RecyclerView.Adapter<RecyclerViewUserCartProductAdapter.ViewHolderStudentList>() {

        // ViewHolder
        inner class ViewHolderStudentList(val itemUsercartListBinding: ItemUsercartListBinding) : RecyclerView.ViewHolder(itemUsercartListBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderStudentList {
            val itemUsercartListBinding = ItemUsercartListBinding.inflate(layoutInflater, parent, false)
            return ViewHolderStudentList(itemUsercartListBinding)
        }

        override fun getItemCount(): Int {
            return cartItems.size
        }

        override fun onBindViewHolder(holder: ViewHolderStudentList, position: Int) {
            val item = cartItems[position]
            // 더미 데이터 바인딩
            holder.itemUsercartListBinding.textViewRecyclerViewProductName.text = item.productName
            holder.itemUsercartListBinding.textViewRecyclerViewProductPrice.text = "${item.productPrice}"

            // Drawable 리소스를 바인딩
            holder.itemUsercartListBinding.imageViewRecyclerViewImage.setImageResource(item.imageResId)
        }
    }
}


