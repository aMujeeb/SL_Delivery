package com.supplier.sl_delivery.ui.adapter.listeners

import android.view.View
import com.supplier.sl_delivery.modals.MenuPaneItem

/**
 * Created by Aurora on 2020-04-04.
 */
interface OnMenuItemSelected {
    fun onMainMenuSelected(mainMenu : MenuPaneItem, menuItem : View)
}
