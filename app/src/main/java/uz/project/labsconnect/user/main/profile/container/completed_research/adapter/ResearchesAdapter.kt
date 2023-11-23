package uz.project.labsconnect.user.main.profile.container.completed_research.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import uz.project.labsconnect.user.main.profile.container.types.QAType
import uz.project.labsconnect.user.main.profile.container.types.ResearchType

class ResearchesAdapter() :
    AsyncListDifferDelegationAdapter<ResearchType>(CategoryDiffutilsCallback()) {

    init {
        delegatesManager.addDelegate(ResearchesAdapterDelegate())
    }


    class CategoryDiffutilsCallback : DiffUtil.ItemCallback<ResearchType>() {
        override fun areItemsTheSame(oldItem: ResearchType, newItem: ResearchType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResearchType, newItem: ResearchType): Boolean {
            return oldItem::class == newItem::class
        }

    }

}