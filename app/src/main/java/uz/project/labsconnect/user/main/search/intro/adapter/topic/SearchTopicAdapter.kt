package uz.project.labsconnect.user.main.search.intro.adapter.topic

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import uz.project.labsconnect.user.main.types.TopicType

class SearchTopicAdapter(private val onClicked: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<TopicType>(CategoryDiffutilsCallback()) {

    init {
        delegatesManager.addDelegate(SearchTopicAdapterDelegate(onClicked))
    }


    class CategoryDiffutilsCallback : DiffUtil.ItemCallback<TopicType>() {
        override fun areItemsTheSame(oldItem: TopicType, newItem: TopicType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TopicType, newItem: TopicType): Boolean {
            return oldItem::class == newItem::class
        }

    }

}