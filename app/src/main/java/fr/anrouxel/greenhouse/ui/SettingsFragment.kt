package fr.anrouxel.greenhouse.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.Navigation
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.android.volley.Request
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.anrouxel.greenhouse.R
import org.json.JSONObject

class SettingsFragment : PreferenceFragmentCompat() {

    private var greenhouse_advanced: Preference? = null
    private var preferences_network: Preference? = null
    private var herb: ListPreference? = null
    private var greenhouse: SwitchPreference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        greenhouse_advanced = findPreference("greenhouse_advanced")
        preferences_network = findPreference("preferences_network")
        herb = findPreference("herb")
        greenhouse = findPreference("greenhouse")

        herb?.isEnabled = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requestList(view)
        requestConfig(view)

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

    private fun requestList(view: View) {
        val sharedPref = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        val networkIP = sharedPref.getString("networkIP", null)

        if (networkIP != null) {
            val url: String = "http://$networkIP:3000/config/herb/fr"
            val queue = Volley.newRequestQueue(requireActivity())
            VolleyLog.DEBUG = true

            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    val entries: ArrayList<String> = ArrayList()
                    val entryValues: ArrayList<String> = ArrayList()

                    for (i in 0 until response.length()) {
                        val jsonObject = response[i] as JSONObject
                        entryValues.add(jsonObject.getString("id"))
                        entries.add(jsonObject.getString("name_fr"))
                    }

                    herb?.entries = entries.toArray(arrayOfNulls<String>(entries.size))
                    herb?.entryValues = entryValues.toArray(arrayOfNulls<String>(entryValues.size))
                    herb?.isEnabled = true
                },
                {
                    herb?.isEnabled = false
                    Log.d("QrFeature", "Error")
                }
            )

            queue.add(jsonArrayRequest)
        } else {
            val action = SettingsFragmentDirections.actionGlobalMozillaQrFeatureFragment("settingsFragment")
            Navigation.findNavController(view).navigate(action)
        }
    }

    private fun requestConfig(view: View) {
        val sharedPref = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        val networkIP = sharedPref.getString("networkIP", null)

        if (networkIP != null) {
            val url: String = "http://$networkIP:3000/config/read"
            val queue = Volley.newRequestQueue(requireActivity())
            VolleyLog.DEBUG = true

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                { response ->
                    Log.d("QrFeature", response.toString())
                    val serre = response.getString("serre")
                    val aromate = response.getString("aromate")
                    greenhouse?.isChecked = serre == "1"
                    herb?.setDefaultValue(aromate)
                },
                {
                    Log.d("QrFeature", "Error")
                }
            )
            queue.add(jsonObjectRequest)
        } else {
            val action = SettingsFragmentDirections.actionGlobalMozillaQrFeatureFragment("settingsFragment")
            Navigation.findNavController(view).navigate(action)
        }
    }
}