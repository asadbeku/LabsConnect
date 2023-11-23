package uz.project.labsconnect.user.main.search.search_item.adapter.equipments

import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.types.EquipmentType

class SearchResultEquipmentsAdapterDelegate(private val onClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<EquipmentType, EquipmentType, SearchResultEquipmentsAdapterDelegate.SearchResultEquipmentsViewHolder>() {

    override fun isForViewType(
        item: EquipmentType,
        items: MutableList<EquipmentType>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): SearchResultEquipmentsViewHolder {

        return SearchResultEquipmentsViewHolder(
            parent.inflate(R.layout.item_equipment), onClicked
        )
    }

    override fun onBindViewHolder(
        item: EquipmentType,
        holder: SearchResultEquipmentsViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class SearchResultEquipmentsViewHolder(
        view: View,
        private val onClicked: (position: Int) -> Unit
    ) : SearchResultEquipmentsBaseHolder(view = view, onClicked) {
        fun bind(equipment: EquipmentType) {
            Log.d("checkAdapter","Item: $equipment")
            bind(
                equipment.equipment_name,
                equipment.organisation_name,
                equipment.typeOrganization,
                equipment.equipment_image,
                equipment.purchases_prices.toString()
            )
        }

    }

}