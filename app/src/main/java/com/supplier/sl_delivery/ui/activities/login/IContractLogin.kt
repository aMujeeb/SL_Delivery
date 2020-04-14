package com.supplier.sl_delivery.ui.activities.login

import com.supplier.sl_delivery.ui.activities.base.IBasePresenter
import com.supplier.sl_delivery.ui.activities.base.IBaseView

/**
 * Created by Aurora on 2020-04-12.
 */
interface IContractLogin {

    interface ILoginView : IBaseView {
        fun showUserNameError(error : Int)
        fun showPasswordError(error: Int)
        fun navigateToMain()
    }

    interface ILoginPresenter<V : ILoginView> : IBasePresenter<V> {
        fun setView(view : ILoginView)
        fun triggerLogin()
        fun setUserName(name : String)
        fun setPassword(password : String)
    }
}
