package uz.project.labsconnect.user.main.home.adapter.labs

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.types.CategoryType
import uz.project.labsconnect.user.main.types.LabsType

class LabsAdapterDelegate(private val onClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<LabsType, LabsType, LabsAdapterDelegate.LabsViewHolder>() {

    override fun isForViewType(
        item: LabsType,
        items: MutableList<LabsType>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): LabsViewHolder {
        return LabsViewHolder(
            parent.inflate(R.layout.item_labs), onClicked
        )
    }

    override fun onBindViewHolder(
        item: LabsType,
        holder: LabsViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class LabsViewHolder(view: View, onClicked: (position: Int) -> Unit) :
        LabsBaseHolder(view = view, onClicked) {

        fun bind(lab: LabsType) {
            bind(lab.name, lab.orgAddress, lab.payment, lab.orgType, lab.orgLogo)
        }

    }

}