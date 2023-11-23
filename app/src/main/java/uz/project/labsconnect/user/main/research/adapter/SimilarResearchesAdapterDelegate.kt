package uz.project.labsconnect.user.main.research.adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.profile.container.types.ResearchType
import uz.project.labsconnect.user.main.types.SimilarResearchType

class SimilarResearchesAdapterDelegate(private val onClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<SimilarResearchType, SimilarResearchType, SimilarResearchesAdapterDelegate.SimilarResearchViewHolder>() {

    override fun isForViewType(
        item: SimilarResearchType,
        items: MutableList<SimilarResearchType>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): SimilarResearchViewHolder {
        return SimilarResearchViewHolder(
            parent.inflate(R.layout.item_similar_researches), onClicked
        )
    }

    override fun onBindViewHolder(
        item: SimilarResearchType,
        holder: SimilarResearchViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class SimilarResearchViewHolder(view: View, onClicked: (position: Int) -> Unit) :
        SimilarResearchesBaseHolder(view = view, onClicked) {

        fun bind(research: SimilarResearchType) {
            bind(research.researchName, research.authors, research.publishedIn, research.summary)
        }

    }

}