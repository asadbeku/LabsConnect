package uz.project.labsconnect.user.main.search.intro.adapter.recomendation

import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import uz.project.labsconnect.MainActivity
import uz.project.labsconnect.R
import uz.project.labsconnect.user.main.home.adapter.inflate
import uz.project.labsconnect.user.main.types.CategoryType
import uz.project.labsconnect.user.main.types.LabsType

class RecommendedAdapterDelegate(private val onClicked: (position: Int) -> Unit) :
    AbsListItemAdapterDelegate<LabsType, LabsType, RecommendedAdapterDelegate.RecommendedViewHolder>() {

    override fun isForViewType(
        item: LabsType,
        items: MutableList<LabsType>,
        position: Int
    ): Boolean {
        return true
    }

    private val displayMetrics = DisplayMetrics()
    private var screenWidth = 0
    override fun onCreateViewHolder(parent: ViewGroup): RecommendedViewHolder {
        (parent.context as MainActivity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenWidth = displayMetrics.widthPixels

        return RecommendedViewHolder(
            parent.inflate(R.layout.item_labs), onClicked
        )
    }

    override fun onBindViewHolder(
        item: LabsType,
        holder: RecommendedViewHolder,
        payloads: MutableList<Any>
    ) {
        val itemPadding = 8 // padding between items
        val itemWidth = (screenWidth - itemPadding) / 1.3

        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = itemWidth.toInt()
        holder.itemView.layoutParams = layoutParams

        holder.bind(item)
    }

    class RecommendedViewHolder(view: View, private val onClicked: (position: Int) -> Unit) :
        RecommendedBaseHolder(view = view, onClicked) {

        fun bind(lab: LabsType) {
            bind(lab.name, lab.orgAddress, lab.payment, lab.orgType, lab.orgLogo)
        }

    }

}