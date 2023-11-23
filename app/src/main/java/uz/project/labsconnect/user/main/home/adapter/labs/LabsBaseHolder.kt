package uz.project.labsconnect.user.main.home.adapter.labs

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.ItemLabsBinding

abstract class LabsBaseHolder(view: View, private val onClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {

    val binding = ItemLabsBinding.bind(view)
    init {
        view.setOnClickListener {
            onClicked(absoluteAdapterPosition)
        }
    }
    fun bind(
        orgName: String,
        orgAddress: String,
        orgPayment: String,
        orgType: Boolean,
        orgLogo: String
    ) {

        binding.orgName.text = orgName
        binding.orgAddress.text = orgAddress


        if (orgPayment != "Free") {
            binding.orgPayment.text = "$orgPayment UZS"
        } else {
            binding.orgPayment.text = orgPayment
        }

        Glide.with(itemView)
            .load(orgLogo)
            .placeholder(R.drawable.app_logo)
            .centerInside()
            .into(binding.orgLogo)

        if (orgType) {
            binding.orgType.text = "Governmental"
            Glide.with(itemView)
                .load(R.drawable.governmetal_icon)
                .placeholder(R.drawable.app_logo)
                .centerInside()
                .into(binding.orgTypeIcon)
        } else {
            binding.orgType.text = "Private"
            Glide.with(itemView)
                .load(R.drawable.private_icon)
                .placeholder(R.drawable.app_logo)
                .centerInside()
                .into(binding.orgTypeIcon)
        }


    }

}