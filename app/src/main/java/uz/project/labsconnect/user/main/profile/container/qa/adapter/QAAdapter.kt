package uz.project.labsconnect.user.main.profile.container.qa.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import uz.project.labsconnect.user.main.profile.container.types.QAType
import uz.project.labsconnect.user.main.types.LabsType

class QAAdapter() :
    AsyncListDifferDelegationAdapter<QAType>(CategoryDiffutilsCallback()) {

    init {
        delegatesManager.addDelegate(QAAdapterDelegate())
    }


    class CategoryDiffutilsCallback : DiffUtil.ItemCallback<QAType>() {
        override fun areItemsTheSame(oldItem: QAType, newItem: QAType): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QAType, newItem: QAType): Boolean {
            return oldItem::class == newItem::class
        }

    }

}