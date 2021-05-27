package fr.anrouxel.greenhouse.ui.settings

import android.os.Bundle
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
}