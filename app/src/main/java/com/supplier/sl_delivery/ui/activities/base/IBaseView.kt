package com.supplier.sl_delivery.ui.activities.base

import com.supplier.sl_delivery.enums.AlertType

/**
 * Created by Aurora on 2020-04-12.
 */
interface IBaseView {
    fun displayGeneralErrorMessage(errMessage: String, alertType : AlertType)
    fun displayGeneralErrorMessage(error : Int, alertType : AlertType)
    fun showProgress()
    fun hideProgress()
    fun hideActionButton()
    fun showActionButton()
}
