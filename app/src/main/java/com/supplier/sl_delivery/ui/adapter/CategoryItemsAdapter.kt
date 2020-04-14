package com.supplier.sl_delivery.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.modals.CategoryItem
import com.supplier.sl_delivery.ui.adapter.viewholders.CategoryItemViewHolder
import com.supplier.sl_delivery.ui.fragments.product_items.IContractProductItems

/**
 * Created by Aurora on 2020-04-14.
 */
class CategoryItemsAdapter(var activity: Activity, var mProductsPresenter : IContractProductItems.IProductsPresenter<IContractProductItems.IProductsItemView>) : RecyclerView.Adapter<CategoryItemViewHolder>() {

    private var            mCategoryItems : ArrayList<CategoryItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        return CategoryItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if (mCategoryItems.isNullOrEmpty()) {
            0
        } else {
            mCategoryItems!!.size
        }
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.onBindView(mCategoryItems!![position])
        holder.itemView.setOnClickListener {
            mProductsPresenter.setSelectedCategoryItem(mCategoryItems!![position])
        }
    }

    fun setItems() {
        mCategoryItems = mProductsPresenter.getProductItems()
    }
}
