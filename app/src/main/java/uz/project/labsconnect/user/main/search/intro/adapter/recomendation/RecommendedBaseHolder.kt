package uz.project.labsconnect.user.main.search.intro.adapter.recomendation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.ItemLabsBinding

abstract class RecommendedBaseHolder(view: View, private val onClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {

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

        binding.orgType.visibility = View.GONE

        if (orgPayment!="Free"){
            binding.orgPayment.text = "$orgPayment UZS"
        }else{
            binding.orgPayment.text = orgPayment
        }

        Glide.with(itemView)
            .load(orgLogo)
            .placeholder(R.drawable.app_logo)
            .centerInside()
            .into(binding.orgLogo)

        if (orgType) {
            Glide.with(itemView)
                .load(R.drawable.governmetal_icon)
                .placeholder(R.drawable.app_logo)
                .centerInside()
                .into(binding.orgTypeIcon)
        } else {
            Glide.with(itemView)
                .load(R.drawable.private_icon)
                .placeholder(R.drawable.app_logo)
                .centerInside()
                .into(binding.orgTypeIcon)
        }


    }

}