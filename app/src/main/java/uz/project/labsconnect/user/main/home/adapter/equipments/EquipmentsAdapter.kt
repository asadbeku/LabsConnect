package uz.project.labsconnect.user.main.home.adapter.equipments

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import uz.project.labsconnect.user.main.search.search_item.adapter.equipments.SearchResultEquipmentsAdapterDelegate
import uz.project.labsconnect.user.main.types.EquipmentType

class EquipmentsAdapter(onClicked: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<EquipmentType>(CategoryDiffutilsCallback()) {

    init {
        delegatesManager.addDelegate(EquipmentsAdapterDelegate(onClicked))
    }


    class CategoryDiffutilsCallback : DiffUtil.ItemCallback<EquipmentType>() {
        override fun areItemsTheSame(oldItem: EquipmentType, newItem: EquipmentType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EquipmentType, newItem: EquipmentType): Boolean {
            return oldItem::class == newItem::class
        }

    }

}