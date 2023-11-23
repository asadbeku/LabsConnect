package uz.project.labsconnect.user.main.equipment

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import uz.project.labsconnect.R
import uz.project.labsconnect.databinding.FragmentEquipmentDetailBinding
import uz.project.labsconnect.databinding.FragmentOrganizationBinding
import uz.project.labsconnect.user.main.equipment.view_model.EquipmentViewModel
import java.time.Instant
import java.time.Year
import java.time.ZoneId

class EquipmentDetailActivity : AppCompatActivity() {

    private lateinit var binding: FragmentEquipmentDetailBinding

    private val viewModel: EquipmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupFragmentSettings()
        bindInfo(getEquipmentId())

    }

    private fun setupBinding() {
        binding = FragmentEquipmentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupFragmentSettings() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.decorView.systemUiVisibility = 0
    }

    private fun getEquipmentId(): Int {
        val value = intent.getStringExtra("key")?.toInt()

        return value ?: 0
    }

    private fun bindInfo(id: Int) {
        viewModel.getEquipmentData(id)

        viewModel.equipmentData.observe(this) {
            binding.equipmentName.text = it.equipment_name
            binding.price.text = it.purchases_prices
            binding.organizationName.text = it.organisation_name
            binding.organizationAddress.text = "Toshkent shaxri"
            binding.description.text = it.description
            binding.condition.text =
                if (it.manufacture_year.toInt() < getYearFromMillis(System.currentTimeMillis())) "New" else "Old"

            binding.count.text = it.quantity.toString()
            binding.manufactureYear.text = it.manufacture_year.toString()
            binding.createdDate.text = it.createdDate.toString()

        }

    }

    private fun getYearFromMillis(millis: Long): Int {
        val instant = Instant.ofEpochMilli(millis)
        val year = Year.from(instant.atZone(ZoneId.systemDefault()))
        return year.value
    }
}