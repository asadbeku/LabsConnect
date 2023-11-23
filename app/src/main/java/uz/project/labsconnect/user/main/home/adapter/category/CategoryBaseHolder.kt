package uz.project.labsconnect.user.main.home.adapter.category

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.withContext
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.ItemCategoryBinding

abstract class CategoryBaseHolder(view: View, private val onClicked: (position: Int) -> Unit) :
    RecyclerView.ViewHolder(view) {

    val binding = ItemCategoryBinding.bind(view)

    init {
        view.setOnClickListener {
            onClicked(absoluteAdapterPosition)
        }
    }

    fun bind(categoryName: String, categoryIcon: String) {

        binding.categoryName.text = categoryName

        Glide.with(itemView)
            .load(categoryIcon)
            .placeholder(R.drawable.app_logo)
            .centerInside()
            .into(binding.categoryIcon)
    }

}