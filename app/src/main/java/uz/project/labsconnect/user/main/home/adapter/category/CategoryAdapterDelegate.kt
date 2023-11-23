package uz.project.labsconnect.user.main.home.adapter.category

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.types.CategoryType

class CategoryAdapterDelegate(private val onClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<CategoryType, CategoryType, CategoryAdapterDelegate.CategoryViewHolder>() {

    override fun isForViewType(
        item: CategoryType,
        items: MutableList<CategoryType>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): CategoryViewHolder {
        return CategoryViewHolder(
            parent.inflate(R.layout.item_category), onClicked
        )
    }

    override fun onBindViewHolder(
        item: CategoryType,
        holder: CategoryViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class CategoryViewHolder(view: View, onClicked: (position: Int) -> Unit) :
        CategoryBaseHolder(view = view, onClicked) {

        fun bind(category: CategoryType) {
            bind(category.name, category.icon)
        }

    }

}