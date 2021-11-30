package com.example.sportreservation.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportreservation.R
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()

    }

    inner class SettingsFragment: PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            findPreference<SwitchPreference>(getString(R.string.notification_key))
                ?.setOnPreferenceChangeListener { _, newValue ->
                    newValue?.let {
                        when(it as Boolean) {
                            true ->  {

                            }
                            false -> {

                            }
                        }
                    }
                    true
                }
        }

    }
}
