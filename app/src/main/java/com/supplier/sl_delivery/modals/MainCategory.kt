package com.supplier.sl_delivery.modals

import com.supplier.sl_delivery.rest.APIClient
import com.supplier.sl_delivery.rest.responses.CategoriesResponse
import com.supplier.sl_delivery.rest.responses.ItemsByCategoryResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Aurora on 2020-04-13.
 */
data class MainCategory(var id : String, var lable : String, var active : Boolean) {

    companion object {
        fun getAllCategories() : Observable<CategoriesResponse?>? {
            return APIClient.getAllCategoriesList()!!.subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread())
        }
    }

    fun getAllItems() : Observable<ItemsByCategoryResponse?>? {
        return APIClient.getAllItemsUnderCategory(this.id)!!.subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())
    }
}
