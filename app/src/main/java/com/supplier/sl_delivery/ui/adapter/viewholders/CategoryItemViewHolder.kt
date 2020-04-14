package com.supplier.sl_delivery.ui.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.supplier.sl_delivery.modals.CategoryItem
import kotlinx.android.synthetic.main.item_category_item.view.*

/**
 * Created by Aurora on 2020-04-14.
 */
class CategoryItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

    fun onBindView(categoryItem: CategoryItem){
        itemView.mLblCategoryName.text = categoryItem.lable
    }
}
