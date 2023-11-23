package uz.project.labsconnect.user.main.profile.container.completed_research.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.ItemQaBinding
import uz.project.labsconnect.databinding.ItemResearchBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

abstract class ResearchesBaseHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemResearchBinding.bind(view)

    fun bind(
        image: String,
        researchName: String,
        organizationName: String
    ) {

        binding.researchName.text = researchName
        binding.researchedOrganization.text = organizationName

        Glide.with(itemView)
            .load(image)
            .placeholder(R.drawable.app_logo)
            .centerInside()
            .into(binding.researchCategoryImage)

    }
}