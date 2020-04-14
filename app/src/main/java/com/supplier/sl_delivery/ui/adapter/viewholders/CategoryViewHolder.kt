package com.supplier.sl_delivery.ui.adapter.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.supplier.sl_delivery.modals.MainCategory
import kotlinx.android.synthetic.main.item_main_category.view.*

/**
 * Created by Aurora on 2020-04-14.
 */
class CategoryViewHolder(item : View) : RecyclerView.ViewHolder(item) {

    fun onBind(category: MainCategory) {
        itemView.mLblCategoryName.text = category.lable
    }
}
