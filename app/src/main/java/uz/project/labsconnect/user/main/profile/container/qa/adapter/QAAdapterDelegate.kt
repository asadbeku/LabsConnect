package uz.project.labsconnect.user.main.profile.container.qa.adapter

import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.profile.container.types.QAType
import uz.project.labsconnect.user.main.types.LabsType

class QAAdapterDelegate :
    AbsListItemAdapterDelegate<QAType, QAType, QAAdapterDelegate.QAViewHolder>() {

    override fun isForViewType(
        item: QAType,
        items: MutableList<QAType>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): QAViewHolder {
        return QAViewHolder(
            parent.inflate(R.layout.item_qa)
        )
    }

    override fun onBindViewHolder(
        item: QAType,
        holder: QAViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class QAViewHolder(view: View) : QABaseHolder(view = view) {

        fun bind(qa: QAType) {
            bind(
                qa.fullName,
                qa.image,
                qa.userMessage,
                qa.date,
                qa.status,
                qa.orgAnswer ?: "",
                qa.answerData ?: "",
                qa.orgName
            )
        }

    }

}