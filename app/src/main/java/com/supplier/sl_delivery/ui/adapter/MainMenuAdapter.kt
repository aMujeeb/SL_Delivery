package com.supplier.sl_delivery.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.supplier.sl_delivery.R
import com.supplier.sl_delivery.databinding.ItemMainMenuBinding
import com.supplier.sl_delivery.modals.MenuPaneItem
import com.supplier.sl_delivery.ui.adapter.listeners.OnMenuItemSelected
import com.supplier.sl_delivery.ui.adapter.viewholders.MenuItemViewHolder
import com.supplier.sl_delivery.view_modals.MainViewModal
import kotlinx.android.synthetic.main.item_main_menu.view.*

/**
 * Created by Aurora on 2020-04-03.
 */
class MainMenuAdapter(var mainViewModal: MainViewModal, var mContext: Context) :
    RecyclerView.Adapter<MenuItemViewHolder>(), OnMenuItemSelected {

    private var mMenuList: ArrayList<MenuPaneItem>? = null
    private var mRowIndex: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        var menuBinding: ItemMainMenuBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_main_menu, parent, false
        )
        return MenuItemViewHolder(menuBinding)
    }

    override fun getItemCount(): Int {
        if (mMenuList.isNullOrEmpty()) {
            return 0
        }
        return mMenuList!!.size
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        if (!mMenuList.isNullOrEmpty()) {
            holder.menuBinding.menuPaneItem = mMenuList!![position]
            holder.menuBinding.menuSelector = this
            if (mRowIndex == position) {
                holder.itemView.setBackgroundColor(mContext.resources.getColor(R.color.nature_green))
            } else {
                holder.itemView.setBackgroundColor(mContext.resources.getColor(R.color.white))
            }
        }
    }

    override fun onMainMenuSelected(mainMenu: MenuPaneItem, menuItem: View) {
        mainViewModal.onMenuItemSelected(mainMenu)
        mRowIndex = mainMenu.menuId
        menuItem.setBackgroundColor(Color.parseColor("#567845"))
        notifyDataSetChanged()
    }

    fun setMenuItems(menuList: ArrayList<MenuPaneItem>) {
        mMenuList = menuList
        notifyDataSetChanged()
    }
}
