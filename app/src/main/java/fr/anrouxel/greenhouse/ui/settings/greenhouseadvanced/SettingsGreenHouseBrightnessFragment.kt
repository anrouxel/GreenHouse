package fr.anrouxel.greenhouse.ui.settings.greenhouseadvanced

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import fr.anrouxel.greenhouse.R

class SettingsGreenHouseBrightnessFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_greenhouse_brightness, rootKey)
    }
}