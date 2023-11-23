package uz.project.labsconnect.user.main.search.intro.adapter.category

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.types.CategoryType

class SearchCategoryAdapterDelegate(private val onClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<CategoryType, CategoryType, SearchCategoryAdapterDelegate.SearchCategoryViewHolder>() {

    override fun isForViewType(
        item: CategoryType,
        items: MutableList<CategoryType>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): SearchCategoryViewHolder {
        return SearchCategoryViewHolder(
            parent.inflate(R.layout.item_category), onClicked
        )
    }

    override fun onBindViewHolder(
        item: CategoryType,
        holder: SearchCategoryViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class SearchCategoryViewHolder(view: View, private val onClicked: (position: Int) -> Unit) :
        SearchCategoryBaseHolder(view = view, onClicked) {

        fun bind(category: CategoryType) {
            bind(category.name, category.icon)
        }

    }

}