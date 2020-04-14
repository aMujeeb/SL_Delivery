package com.supplier.sl_delivery.ui.fragments.products

import com.supplier.sl_delivery.modals.MainCategory
import com.supplier.sl_delivery.ui.activities.base.IBasePresenter
import com.supplier.sl_delivery.ui.activities.base.IBaseView

/**
 * Created by Aurora on 2020-04-13.
 */
interface IContractProductsView {

    interface IProductsView : IBaseView {
        fun refreshView()
        fun showNoResults()
        fun launchItemsView()
    }

    interface IProductsPresenter<V : IProductsView> : IBasePresenter<V>{
        fun setView(view : IProductsView)
        fun requestProducts()
        fun getProducts() : ArrayList<MainCategory>?
        fun setSelectedCategory(mainCategory: MainCategory)
        fun getSelectedCategory() : MainCategory?
    }
}
