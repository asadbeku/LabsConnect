package uz.project.labsconnect.user.main.home.adapter.labs

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import uz.project.labsconnect.user.main.types.CategoryType
import uz.project.labsconnect.user.main.types.LabsType

class LabsAdapter(onClicked: (position: Int) -> Unit) :
    AsyncListDifferDelegationAdapter<LabsType>(CategoryDiffutilsCallback()) {

    init {
        delegatesManager.addDelegate(LabsAdapterDelegate(onClicked))
    }


    class CategoryDiffutilsCallback : DiffUtil.ItemCallback<LabsType>() {
        override fun areItemsTheSame(oldItem: LabsType, newItem: LabsType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LabsType, newItem: LabsType): Boolean {
            return oldItem::class == newItem::class
        }

    }

}