package uz.project.labsconnect.user.main.search.search_item.adapter.equipments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.ItemEquipmentBinding

abstract class SearchResultEquipmentsBaseHolder(view: View, private val onClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {

    val binding = ItemEquipmentBinding.bind(view)
    init {
        view.setOnClickListener {
            onClicked(absoluteAdapterPosition)
        }
    }
    fun bind(
        name: String,
        orgName: String,
        typeOrg: Boolean,
        equipmentImage: String,
        equipmentPurchasePrices: String
    ) {
        binding.equipmentName.text = name
        binding.equipmentOrganizationName.text = orgName

        if (equipmentPurchasePrices!="Free"){
            binding.equipmentPayment.text = "$equipmentPurchasePrices UZS"
        }else{
            binding.equipmentPayment.text = equipmentPurchasePrices
        }

        Glide.with(itemView)
            .load(equipmentImage)
            .placeholder(R.drawable.app_logo)
            .centerInside()
            .into(binding.equipmentOrganizationImage)

        if (typeOrg) {

            Glide.with(itemView)
                .load(R.drawable.governmetal_icon)
                .placeholder(R.drawable.app_logo)
                .centerInside()
                .into(binding.equipmentOrganizationImage)
        } else {
            Glide.with(itemView)
                .load(R.drawable.private_icon)
                .placeholder(R.drawable.app_logo)
                .centerInside()
                .into(binding.equipmentOrganizationImage)
        }
    }
}