package com.supplier.sl_delivery.ui.activities.base

/**
 * Created by Aurora on 2020-04-12.
 */
interface IBasePresenter <V : IBaseView> {
    fun onDestroy()
    fun onAttach(iView: V)
}
