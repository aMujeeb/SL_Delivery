package com.supplier.sl_delivery.ui.fragments.products

import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.enums.AlertType
import com.supplier.sl_delivery.modals.MainCategory
import com.supplier.sl_delivery.rest.responses.CategoriesResponse
import com.supplier.sl_delivery.ui.activities.base.BasePresenter
import com.supplier.sl_delivery.utils.LoggerUtil
import io.reactivex.observers.DisposableObserver

/**
 * Created by Aurora on 2020-04-13.
 */
class ImplProductsPresenter<V : IContractProductsView.IProductsView> : BasePresenter<V>(),
    IContractProductsView.IProductsPresenter<V> {

    private lateinit var mProductsView: IContractProductsView.IProductsView
    private var mMainCategories: ArrayList<MainCategory>? = null
    private var mDisposableObserver: DisposableObserver<CategoriesResponse>? = null
    private var mSelectedCategory : MainCategory? = null

    override fun setView(view: IContractProductsView.IProductsView) {
        mProductsView = view
        mProductsView.showProgress()
        requestProducts()
    }

    override fun requestProducts() {
        MainCategory.getAllCategories()?.subscribeWith(createProductsObserver())
    }

    private fun createProductsObserver(): DisposableObserver<CategoriesResponse>?{
        mDisposableObserver = object : DisposableObserver<CategoriesResponse>() {
            override fun onComplete() {
                LoggerUtil.logMessage("Completed Observer")
            }

            override fun onNext(t: CategoriesResponse) {
                mProductsView.hideProgress()
                if (t.data != null) {
                    mMainCategories = t.data
                    mProductsView.refreshView()
                } else {
                    mProductsView.showNoResults()
                }
            }

            override fun onError(e: Throwable) {
                LoggerUtil.logMessage("Error Observer")
                mProductsView.hideProgress()
                mProductsView.displayGeneralErrorMessage(
                    R.string.categories_error,
                    AlertType.ALERT_TYPE_ERROR
                )
            }
        }
        return mDisposableObserver
    }

    override fun getProducts(): ArrayList<MainCategory>? {
        return mMainCategories
    }

    override fun setSelectedCategory(mainCategory: MainCategory) {
        mSelectedCategory = mainCategory
        mProductsView.launchItemsView()
    }

    override fun onDestroy() {
        if(mDisposableObserver != null){
            mDisposableObserver!!.dispose()
        }
    }

    override fun getSelectedCategory(): MainCategory? {
        return mSelectedCategory
    }
}
