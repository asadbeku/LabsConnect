package uz.project.labsconnect.user.main.home.adapter.equipments

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.MainActivity
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.types.EquipmentType

class EquipmentsAdapterDelegate(private val onClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<EquipmentType, EquipmentType, EquipmentsAdapterDelegate.EquipmentsViewHolder>() {

    override fun isForViewType(
        item: EquipmentType,
        items: MutableList<EquipmentType>,
        position: Int
    ): Boolean {
        return true
    }

    private val displayMetrics = DisplayMetrics()
    private var screenWidth = 0

    override fun onCreateViewHolder(parent: ViewGroup): EquipmentsViewHolder {
        (parent.context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels
        return EquipmentsViewHolder(
            parent.inflate(R.layout.item_equipment), onClicked
        )
    }

    override fun onBindViewHolder(
        item: EquipmentType,
        holder: EquipmentsViewHolder,
        payloads: MutableList<Any>
    ) {
        val itemPadding = 8 // padding between items
        val itemWidth = (screenWidth - itemPadding) / 1.8

        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = itemWidth.toInt()
        holder.itemView.layoutParams = layoutParams

        holder.bind(item)
    }

    class EquipmentsViewHolder(view: View, onClicked: (position: Int) -> Unit) :
        EquipmentsBaseHolder(view = view, onClicked) {

        fun bind(equipment: EquipmentType) {
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