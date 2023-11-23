package uz.project.labsconnect.utils

import android.content.Context

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("OrganizationData", Context.MODE_PRIVATE)

    fun addOrganizationData(name: String, data: String) {
        with(sharedPreferences.edit()) {
            putString(name, data)
            apply()
        }
    }

    fun deleteOrganizationData() {
        sharedPreferences.edit().clear().apply()
    }

    fun getDataByKey(key: String): String {
        return sharedPreferences.getString(key, "Not founded") ?: "Not founded"
    }


}