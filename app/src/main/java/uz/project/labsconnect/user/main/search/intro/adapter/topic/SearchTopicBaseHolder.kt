package uz.project.labsconnect.user.main.search.intro.adapter.topic

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import uz.project.labsconnect.databinding.ItemTopicBinding

abstract class SearchTopicBaseHolder(view: View, private val onClicked: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {

    val binding = ItemTopicBinding.bind(view)
    init {
        view.setOnClickListener {
            onClicked(absoluteAdapterPosition)
        }
    }
    fun bind(topicId: Int, topicName: String) {

        binding.topicName.text = topicName

    }

}