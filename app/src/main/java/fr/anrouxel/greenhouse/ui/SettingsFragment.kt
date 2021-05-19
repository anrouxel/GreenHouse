package fr.anrouxel.greenhouse.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.android.volley.Request
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.anrouxel.greenhouse.R
import fr.anrouxel.greenhouse.model.AromateList

class SettingsFragment : PreferenceFragmentCompat() {

    private var greenhouse_advanced: Preference? = null
    private var preferences_network: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        greenhouse_advanced = findPreference("greenhouse_advanced")
        preferences_network = findPreference("preferences_network")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestData(view)

        greenhouse_advanced?.setOnPreferenceClickListener {
            val action = SettingsFragmentDirections.actionSettingsFragmentToSettingsGreenHouseAdvancedFragment()
            Navigation.findNavController(view).navigate(action)
            false
        }

        preferences_network?.setOnPreferenceClickListener {
            Log.d("Preferences", "preferences_network")
            val action = SettingsFragmentDirections.actionGlobalMozillaQrFeatureFragment("settingsFragment")
            Navigation.findNavController(view).navigate(action)
            false
        }
    }

    private fun requestData(view: View) {
        val sharedPref = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        val networkIP = sharedPref.getString("networkIP", null)

        if (networkIP != null) {
            val url = "http://$networkIP:3000/config/herb/fr"

            Log.d("QrFeature", url)

            val queue = Volley.newRequestQueue(requireActivity())
            VolleyLog.DEBUG = true

            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    Log.d("QrFeature", response.toString())
                    val type = object : TypeToken<List<AromateList>>() {}.type
                    val list: List<AromateList> = Gson().fromJson(response.toString(), type)

                    Log.d("QrFeature", list.toString())
                }
            ) { Log.d("QrFeature", "error") }

            queue.add(jsonArrayRequest)
        } else {
            val action = SettingsFragmentDirections.actionGlobalMozillaQrFeatureFragment("settingsFragment")
            Navigation.findNavController(view).navigate(action)
        }
    }
}