package com.supplier.sl_delivery.dummy

import android.content.Context
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.modals.MenuPaneItem

/**
 * Created by Aurora on 2020-03-29.
 */
class DummyDataManager {
    companion object {

        fun getMainMenuListItems(mContext : Context): ArrayList<MenuPaneItem> {
            var menuItemList = ArrayList<MenuPaneItem>()
            menuItemList.add(MenuPaneItem(0, mContext.resources.getDrawable(R.drawable.ic_home), R.string.home))
            menuItemList.add(MenuPaneItem(1, mContext.resources.getDrawable(R.drawable.ic_product), R.string.products))
            menuItemList.add(MenuPaneItem(2, mContext.resources.getDrawable(R.drawable.ic_orders), R.string.orders))
            menuItemList.add(MenuPaneItem(3, mContext.resources.getDrawable(R.drawable.ic_storage), R.string.inventory))
            menuItemList.add(MenuPaneItem(3, mContext.resources.getDrawable(R.drawable.ic_reports), R.string.reports))
            menuItemList.add(MenuPaneItem(4, mContext.resources.getDrawable(R.drawable.ic_settings), R.string.settings))
            return menuItemList
        }
    }
}
