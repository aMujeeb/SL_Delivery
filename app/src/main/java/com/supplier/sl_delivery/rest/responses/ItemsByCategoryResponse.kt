package com.supplier.sl_delivery.rest.responses

import com.supplier.sl_delivery.modals.CategoryItem

/**
 * Created by Aurora on 2020-04-14.
 */
class ItemsByCategoryResponse : BaseResponse() {
    var data : ArrayList<CategoryItem>? = null
}
