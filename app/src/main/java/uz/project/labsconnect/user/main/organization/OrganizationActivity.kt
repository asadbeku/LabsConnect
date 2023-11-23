package uz.project.labsconnect.user.main.organization

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import uz.project.labsconnect.R
import uz.project.labsconnect.utils.SharedPreferencesHelper
import uz.project.labsconnect.databinding.FragmentOrganizationBinding
import uz.project.labsconnect.user.main.organization.main_tab.OrganizationTabAdapter
import uz.project.labsconnect.user.main.organization.view_model.OrganizationViewModel
import uz.project.labsconnect.utils.OnEventSelectedListener

class OrganizationActivity : AppCompatActivity() {

    private lateinit var binding: FragmentOrganizationBinding
    private val viewModel: OrganizationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupFragmentSettings()

        bindData(displayLikesCount())

    }

    private fun setupBinding() {
        binding = FragmentOrganizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupFragmentSettings() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.decorView.systemUiVisibility = 0
    }

    private fun displayLikesCount(): Int {
        val value = intent.getStringExtra("key")?.toInt()
        binding.likesCount.text = value.toString()

        return value ?: 0
    }

    private fun bindTabContainer() {
        val tabNames: Array<String> = arrayOf("ABOUT", "EQUIPMENTS", "Q&A")

        val viewPager2 = binding.tabViewpager
        viewPager2.adapter = OrganizationTabAdapter(this)

        TabLayoutMediator(binding.tabLayout, viewPager2) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

    private fun bindData(id: Int) {
        viewModel.getOrganizationInfo(id)
        viewModel.organizationInfo.observe(this) { info ->
            bindTabContainer()

            SharedPreferencesHelper(context = applicationContext).apply {

//                deleteOrganizationData()

                addOrganizationData("description", info.description)
                addOrganizationData("chiefName", info.chief_name)
                addOrganizationData("email", info.contact_email.toString())
                addOrganizationData("tel", info.contact_phone.toString())
                addOrganizationData("address", info.address_line)
                addOrganizationData("web", info.web.toString())
            }

            binding.name.text = info.name
            binding.likesCount.text = "0"
            binding.shares.text = "0"

            Glide.with(this)
                .load(info.photo)
                .placeholder(R.drawable.lab_image)
                .centerCrop()
                .into(binding.orgPhoto)
        }
    }
}