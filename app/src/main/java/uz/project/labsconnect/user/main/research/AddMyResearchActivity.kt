package uz.project.labsconnect.user.main.research

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import uz.project.labsconnect.databinding.ActivityAddMyResearchBinding

class AddMyResearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMyResearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMyResearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        addAuthors()
        binding.saveButton.setOnClickListener {
            Log.d("checkOnClick", "Clicked end icon ${getChipNames()}")
        }
    }

    private fun addAuthors() {
        binding.authorsResearch.setEndIconOnClickListener {
            Log.d("checkOnClick", "Clicked end icon")

            val author = binding.authorsResearch.editText?.text.toString().trim()

            if (author.isNotEmpty()) {
                binding.chipGroup.addView(createChip(author) as View)
                binding.authorsResearch.editText?.text?.clear()
            }
        }

    }

    private fun createChip(text: String): Chip {
        val chip = Chip(this)
        chip.text = text
        chip.isClickable = true
        chip.isCheckable = false
        chip.isChipIconVisible = false
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener { binding.chipGroup.removeView(chip as View) }

        return chip
    }

    private fun getChipNames(): List<String> {
        val chipNames = mutableListOf<String>()
        for (i in 0 until binding.chipGroup.childCount) {
            val chip = binding.chipGroup.getChildAt(i) as Chip
            chipNames.add(chip.text.toString())
        }
        return chipNames
    }


}