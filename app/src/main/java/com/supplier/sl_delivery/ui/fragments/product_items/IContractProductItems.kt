package com.supplier.sl_delivery.ui.fragments.product_items

import com.supplier.sl_delivery.modals.CategoryItem
import com.supplier.sl_delivery.ui.activities.base.IBasePresenter
import com.supplier.sl_delivery.ui.activities.base.IBaseView

/**
 * Created by Aurora on 2020-04-14.
 */
interface IContractProductItems {

    interface IProductsItemView : IBaseView {
        fun refreshList()
        fun showNoResults()
    }

    interface IProductsPresenter<V : IProductsItemView> : IBasePresenter<V> {
        fun setView(view : IProductsItemView)
        fun requestProductItems()
        fun getProductItems() : ArrayList<CategoryItem>?
        fun setSelectedCategoryItem(categoryItem : CategoryItem)
        fun getSelectedCategoryItem() : CategoryItem?
    }
}
