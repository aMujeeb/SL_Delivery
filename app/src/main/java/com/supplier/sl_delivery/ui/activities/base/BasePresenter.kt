package com.supplier.sl_delivery.ui.activities.base

/**
 * Created by Aurora on 2020-04-12.
 */
open abstract class BasePresenter <V : IBaseView> : IBasePresenter<V> {
    private var                    mBaseView : V? = null

    override fun onDestroy() {
        mBaseView = null
    }

    override fun onAttach(iView: V) {
        mBaseView = iView
    }

    open fun getBaseView() : V {
        return mBaseView!!
    }

    private fun isViewAttached(): Boolean {
        return mBaseView != null
    }

    open fun checkViewAttached() {
        if (!isViewAttached()) throw ViewNotAttachedException()
    }

    class ViewNotAttachedException :
        RuntimeException("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter")
}
