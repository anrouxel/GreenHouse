package fr.anrouxel.greenhouse.ui.settings.greenhouseadvanced

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import fr.anrouxel.greenhouse.R

class SettingsGreenHouseCO2Fragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_greenhouse_co2, rootKey)
    }
}