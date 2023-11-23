package uz.project.labsconnect.user.main.research.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.ItemResearchBinding
import uz.project.labsconnect.databinding.ItemSimilarResearchesBinding

abstract class SimilarResearchesBaseHolder(
    view: View,
    private val onClicked: (position: Int) -> Unit
) : RecyclerView.ViewHolder(view) {

    val binding = ItemSimilarResearchesBinding.bind(view)
    init {
        view.setOnClickListener {
            onClicked(absoluteAdapterPosition)
        }
    }
    fun bind(
        title: String,
        author: String,
        publishedIn: String,
        summary: String
    ) {

        binding.researchTitle.text = title
        binding.researchAuthors.text = "Author's: $author"
        binding.researchPublishedIn.text = "Published in: $publishedIn"
        binding.researchSummary.text = "Summary: $summary"


    }
}