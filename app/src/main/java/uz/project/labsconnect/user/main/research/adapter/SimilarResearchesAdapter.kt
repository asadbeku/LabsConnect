package uz.project.labsconnect.user.main.research.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import uz.project.labsconnect.user.main.types.SimilarResearchType

class SimilarResearchesAdapter(private val onClicked: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<SimilarResearchType>(CategoryDiffutilsCallback()) {

    init {
        delegatesManager.addDelegate(SimilarResearchesAdapterDelegate(onClicked))
    }


    class CategoryDiffutilsCallback : DiffUtil.ItemCallback<SimilarResearchType>() {
        override fun areItemsTheSame(oldItem: SimilarResearchType, newItem: SimilarResearchType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SimilarResearchType, newItem: SimilarResearchType): Boolean {
            return oldItem::class == newItem::class
        }

    }

}