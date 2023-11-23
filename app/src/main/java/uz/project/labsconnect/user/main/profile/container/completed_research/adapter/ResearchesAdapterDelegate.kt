package uz.project.labsconnect.user.main.profile.container.completed_research.adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.profile.container.types.QAType
import uz.project.labsconnect.user.main.profile.container.types.ResearchType

class ResearchesAdapterDelegate :
    AbsListItemAdapterDelegate<ResearchType, ResearchType, ResearchesAdapterDelegate.QAViewHolder>() {

    override fun isForViewType(
        item: ResearchType,
        items: MutableList<ResearchType>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): QAViewHolder {
        return QAViewHolder(
            parent.inflate(R.layout.item_research)
        )
    }

    override fun onBindViewHolder(
        item: ResearchType,
        holder: QAViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class QAViewHolder(view: View) : ResearchesBaseHolder(view = view) {

        fun bind(research: ResearchType) {
            bind(research.categoryImage, research.researchName, research.organizationName)
        }

    }

}