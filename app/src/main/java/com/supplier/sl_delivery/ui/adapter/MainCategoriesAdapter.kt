package com.supplier.sl_delivery.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.modals.MainCategory
import com.supplier.sl_delivery.ui.adapter.viewholders.CategoryViewHolder
import com.supplier.sl_delivery.ui.fragments.products.IContractProductsView

/**
 * Created by Aurora on 2020-04-14.
 */
class MainCategoriesAdapter(
    var activity: Activity,
    var mProductsPresenter: IContractProductsView.IProductsPresenter<IContractProductsView.IProductsView>
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private var mMainCategories: ArrayList<MainCategory>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_category, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (mMainCategories.isNullOrEmpty()) {
            0
        } else {
            mMainCategories!!.size
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(mMainCategories!![position])
        holder.itemView.setOnClickListener {
            mProductsPresenter.setSelectedCategory(mMainCategories!![position])
        }
    }

    fun setItems() {
        mMainCategories = mProductsPresenter.getProducts()
    }
}
