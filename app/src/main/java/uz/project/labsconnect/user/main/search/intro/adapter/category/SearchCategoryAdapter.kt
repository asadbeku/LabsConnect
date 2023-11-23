package uz.project.labsconnect.user.main.search.intro.adapter.category

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import uz.project.labsconnect.user.main.types.CategoryType

class SearchCategoryAdapter(private val onClicked: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<CategoryType>(CategoryDiffutilsCallback()) {

    init {
        delegatesManager.addDelegate(SearchCategoryAdapterDelegate(onClicked))
    }


    class CategoryDiffutilsCallback : DiffUtil.ItemCallback<CategoryType>() {
        override fun areItemsTheSame(oldItem: CategoryType, newItem: CategoryType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryType, newItem: CategoryType): Boolean {
            return oldItem::class == newItem::class
        }

    }

}