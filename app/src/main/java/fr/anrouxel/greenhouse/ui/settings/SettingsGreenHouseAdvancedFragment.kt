package fr.anrouxel.greenhouse.ui.settings

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import fr.anrouxel.greenhouse.R

class SettingsGreenHouseAdvancedFragment : PreferenceFragmentCompat() {

    private var brightness: Preference? = null
    private var temperature: Preference? = null
    private var humidity_air: Preference? = null
    private var earth_moisture: Preference? = null
    private var o2: Preference? = null
    private var co2: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_greenhouse_advanced, rootKey)

        brightness = findPreference("brightness")
        temperature = findPreference("temperature")
        humidity_air = findPreference("humidity_air")
        earth_moisture = findPreference("earth_moisture")
        o2 = findPreference("o2")
        co2 = findPreference("co2")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        brightness?.setOnPreferenceClickListener {
            val action = SettingsGreenHouseAdvancedFragmentDirections.actionSettingsGreenHouseAdvancedFragmentToSettingsGreenHouseBrightnessFragment()
            Navigation.findNavController(view).navigate(action)
            false
        }

        temperature?.setOnPreferenceClickListener {
            val action = SettingsGreenHouseAdvancedFragmentDirections.actionSettingsGreenHouseAdvancedFragmentToSettingsGreenHouseTemperatureFragment()
            Navigation.findNavController(view).navigate(action)
            false
        }

        humidity_air?.setOnPreferenceClickListener {
            val action = SettingsGreenHouseAdvancedFragmentDirections.actionSettingsGreenHouseAdvancedFragmentToSettingsGreenHouseHumidityAirFragment()
            Navigation.findNavController(view).navigate(action)
            false
        }

        earth_moisture?.setOnPreferenceClickListener {
            val action = SettingsGreenHouseAdvancedFragmentDirections.actionSettingsGreenHouseAdvancedFragmentToSettingsGreenHouseEarthMoistureFragment()
            Navigation.findNavController(view).navigate(action)
            false
        }

        o2?.setOnPreferenceClickListener {
            val action = SettingsGreenHouseAdvancedFragmentDirections.actionSettingsGreenHouseAdvancedFragmentToSettingsGreenHouseO2Fragment()
            Navigation.findNavController(view).navigate(action)
            false
        }

        co2?.setOnPreferenceClickListener {
            val action = SettingsGreenHouseAdvancedFragmentDirections.actionSettingsGreenHouseAdvancedFragmentToSettingsGreenHouseCO2Fragment()
            Navigation.findNavController(view).navigate(action)
            false
        }
    }
}