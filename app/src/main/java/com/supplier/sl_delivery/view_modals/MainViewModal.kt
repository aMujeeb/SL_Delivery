package com.supplier.sl_delivery.view_modals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.supplier.sl_delivery.dummy.DummyDataManager
import com.supplier.sl_delivery.modals.MenuPaneItem
import com.supplier.sl_delivery.ui.actions.ActionLiveData

/**
 * Created by Aurora on 2020-04-04.
 */
class MainViewModal(application: Application) : AndroidViewModel(application)  {

    fun init() {}
    var mSelectedMenuItem = ActionLiveData<Int>()

    fun getAllMainMenuItems() : ArrayList<MenuPaneItem> {
        return DummyDataManager.getMainMenuListItems(getApplication())
    }

    fun onMenuItemSelected(menuItem : MenuPaneItem){
        mSelectedMenuItem.value = menuItem.menuId
    }
}
