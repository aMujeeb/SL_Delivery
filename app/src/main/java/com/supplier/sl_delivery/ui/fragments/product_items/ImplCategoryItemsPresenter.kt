package com.supplier.sl_delivery.ui.fragments.product_items

import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.enums.AlertType
import com.supplier.sl_delivery.modals.CategoryItem
import com.supplier.sl_delivery.rest.responses.ItemsByCategoryResponse
import com.supplier.sl_delivery.ui.activities.base.BasePresenter
import com.supplier.sl_delivery.ui.fragments.products.IContractProductsView
import com.supplier.sl_delivery.utils.LoggerUtil
import io.reactivex.observers.DisposableObserver

/**
 * Created by Aurora on 2020-04-14.
 */
class ImplCategoryItemsPresenter<V : IContractProductItems.IProductsItemView>(var mProductsPresenter : IContractProductsView.IProductsPresenter<IContractProductsView.IProductsView>) : BasePresenter<V>(), IContractProductItems.IProductsPresenter<V> {

    private lateinit var        mCategoryItemsView : IContractProductItems.IProductsItemView
    private var                 mCategoryItems : ArrayList<CategoryItem>? = null
    private var                 mSelectedCategoryItem : CategoryItem? = null
    private var                 mDisposableObserver: DisposableObserver<ItemsByCategoryResponse>? = null

    override fun setView(view: IContractProductItems.IProductsItemView) {
        mCategoryItemsView = view
        requestProductItems()
    }

    override fun requestProductItems() {
        mCategoryItemsView.showProgress()
        mProductsPresenter.getSelectedCategory()!!.getAllItems()!!.subscribeWith(createProductItemsObserver())
    }

    private fun createProductItemsObserver(): DisposableObserver<ItemsByCategoryResponse>? {
        mDisposableObserver = object : DisposableObserver<ItemsByCategoryResponse>() {
            override fun onComplete() {
                LoggerUtil.logMessage("Completed Observer")
            }

            override fun onNext(t: ItemsByCategoryResponse) {
                mCategoryItemsView.hideProgress()
                if (t.data != null) {
                    mCategoryItems = t.data
                    mCategoryItemsView.refreshList()
                } else {
                    mCategoryItemsView.showNoResults()
                }
            }

            override fun onError(e: Throwable) {
                LoggerUtil.logMessage("Error Observer")
                mCategoryItemsView.hideProgress()
                mCategoryItemsView.displayGeneralErrorMessage(
                    R.string.categories_error,
                    AlertType.ALERT_TYPE_ERROR
                )
            }
        }
        return mDisposableObserver
    }

    override fun getProductItems(): ArrayList<CategoryItem>? {
        return mCategoryItems
    }

    override fun setSelectedCategoryItem(categoryItem: CategoryItem) {
        mSelectedCategoryItem = categoryItem
    }

    override fun getSelectedCategoryItem(): CategoryItem? {
        return mSelectedCategoryItem
    }

    override fun onDestroy() {
        if(mDisposableObserver != null){
            mDisposableObserver!!.dispose()
        }
    }
}
