package com.example.frume.fragment.user_fragment.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frume.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserCategoryDetailBinding
import com.example.frume.databinding.ItemProductBinding
import com.example.frume.fragment.SubMainFragmentName
import com.example.frume.fragment_main.BottomNavMainFragment
import com.example.frume.util.ProductInfoType


class UserCategoryDetailFragment(val bottomNavMainFragment : BottomNavMainFragment) : Fragment() {

    lateinit var homeActivity: HomeActivity
    lateinit var fragmentUserCategoryDetailBinding: FragmentUserCategoryDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentUserCategoryDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_category_detail, container, false)

        homeActivity = activity as HomeActivity


        // 툴바 설정(상단이름 + 뒤로가기 구현)
        settingToolbar()


        // RecyclerView 설정
        settingRecyclerView()

        return fragmentUserCategoryDetailBinding.root
    }

    fun settingToolbar(){
        // 과일 카테고리 이름을 가져와 툴바 바꾸기
        val selectedFruit = arguments?.getString("selectedFruit") ?: "알 수 없는 과일"
        fragmentUserCategoryDetailBinding.toolbarUserCategoryDetail.title = "$selectedFruit"


        // 툴바 뒤로가기 버튼 클릭
        fragmentUserCategoryDetailBinding.toolbarUserCategoryDetail.setNavigationOnClickListener {
            // 뒤로가기 처리
            homeActivity.supportFragmentManager.popBackStack()
        }
    }

    private fun settingRecyclerView() {
        fragmentUserCategoryDetailBinding.apply {
            recyclerViewUserCategoryDetail.adapter = ProductRecyclerViewAdapter(createDummyData()) { product ->
                openProductInfoFragment(product)
            }
            recyclerViewUserCategoryDetail.layoutManager = GridLayoutManager(context, 2) // 2열 그리드
           // val divider = MaterialDividerItemDecoration(context, MaterialDividerItemDecoration.VERTICAL)
           // recyclerViewUserCategoryDetail.addItemDecoration(divider)
        }
    }

    private fun createDummyData(): List<Product> {
        return listOf(
            Product(1, "딸기", "유기농으로 키워 신선합니다.", R.mipmap.ic_launcher),
            Product(2, "사과", "달콤한 사과입니다.", R.mipmap.ic_launcher),
            Product(3, "포도", "씨 없는 달콤한 포도입니다.", R.mipmap.ic_launcher),
            Product(4, "망고", "열대과일의 여왕, 신선한 망고입니다.", R.mipmap.ic_launcher),
            Product(5, "블루베리", "항산화 성분이 가득한 블루베리.", R.mipmap.ic_launcher)
        )
    }

    private fun openProductInfoFragment(product: Product) {
//        val fragment = UserProductInfoFragment().apply {
//            arguments = Bundle().apply {
//                // 넘어가는거 테스트용..
//                putString("productName", product.title)
//            }
//        }
        val dataBundle = Bundle()

        // 어디로 가야하는지 -> productinfo

        //dataBundle.putInt("SubMainFragmentName",1)
        dataBundle.putInt("ProductInfoType", ProductInfoType.USER_PRODUCT_INFO_TYPE.number)
        //dataBundle.putString("productName", product.title)

        Log.d("test100","UserCategoryDetailFragment putint databundle 완성 combinationFragment.replaceFragment 보내기->  ${dataBundle}")

        //userCategoryFragment.bottomNavFragment.combinationFragment.replaceFragment(SubMainFragmentName.PRODUCT_MAIN_FRAGMENT,false,true,dataBundle)
        // 원래 있던 코드
        // bottomNavMainFragment.bottomNavFragment.combinationFragment.replaceFragment(SubMainFragmentName.PRODUCT_MAIN_FRAGMENT,true,true,dataBundle)
        // 수정 코드
        bottomNavMainFragment.combinationFragment.replaceFragment(SubMainFragmentName.PRODUCT_MAIN_FRAGMENT,true,true,dataBundle)
    }

}

// 데이터 클래스 정의
data class Product(val id: Int, val title: String, val description: String, val imageResId: Int)

class ProductRecyclerViewAdapter(
    private val productList: List<Product>, // 데이터 리스트
    private val onItemClick: (Product) -> Unit // 클릭 리스너
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    // ViewHolder 클래스
    inner class ProductViewHolder(val itemProductBinding : ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // DataBinding 초기화
        val binding = DataBindingUtil.inflate<ItemProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )
        val viewHolder = ProductViewHolder(binding)

        // 클릭 리스너 설정
        binding.root.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClick(productList[position])
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.itemProductBinding.apply {
            textViewItemProductTitle.text = product.title
            textViewItemProductDescription.text = product.description
            imageViewItemProductThumbNail.setImageResource(product.imageResId)
        }
    }

    override fun getItemCount(): Int = productList.size
}