package uz.project.labsconnect.user.main.profile.container.qa.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.ItemQaBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

abstract class QABaseHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemQaBinding.bind(view)

    fun bind(
        fullName: String,
        image: String,
        userMessage: String,
        data: String,
        status: String,
        orgAnswer: String,
        answerData: String,
        orgName: String
    ) {

        binding.fullName.text = fullName
        binding.userMessage.text = userMessage

        binding.data.text = getMillisecondDate(data)


        if (status == "1") {
            binding.answerContainer.visibility = View.VISIBLE
            binding.status.text = "Answered by ${getOrganizationInitials(orgName)}"

            binding.orgMessage.text = orgAnswer
            binding.answerData.text = getMillisecondDate(answerData)
        } else {
            binding.status.text = "Not answered by ${getOrganizationInitials(orgName)}"
            binding.status.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.yellowColor
                )
            )
            binding.answerContainer.visibility = View.GONE
        }

        Glide.with(itemView)
            .load(image)
            .placeholder(R.drawable.app_logo)
            .centerInside()
            .into(binding.userImage)

    }

    private fun getOrganizationInitials(organizationName: String): String {
        val words = organizationName.split(" ")
            .filterNot { it.equals("of", ignoreCase = true) } // Exclude "of"
        return words.joinToString("") { it.take(1).uppercase(Locale.getDefault()) }
    }

    private fun getMillisecondDate(data: String): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val date = Date(data.toLong())
        return sdf.format(date)
    }

}