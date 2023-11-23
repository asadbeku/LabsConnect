package uz.project.labsconnect.user.main.search.intro.adapter.topic

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.types.TopicType

class SearchTopicAdapterDelegate(private val onClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<TopicType, TopicType, SearchTopicAdapterDelegate.SearchTopicViewHolder>() {

    override fun isForViewType(
        item: TopicType,
        items: MutableList<TopicType>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): SearchTopicViewHolder {
        return SearchTopicViewHolder(
            parent.inflate(R.layout.item_topic), onClicked
        )
    }

    override fun onBindViewHolder(
        item: TopicType,
        holder: SearchTopicViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class SearchTopicViewHolder(view: View, private val onClicked: (position: Int) -> Unit) :
        SearchTopicBaseHolder(view = view, onClicked) {

        fun bind(topic: TopicType) {
            bind(topic.id, topic.name)
        }

    }

}