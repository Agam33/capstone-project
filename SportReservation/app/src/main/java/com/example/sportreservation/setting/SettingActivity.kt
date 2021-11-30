package com.example.sportreservation.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sportreservation.R
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.sportreservation.notification.NotificationOrder

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
    }

    class SettingsFragment: PreferenceFragmentCompat() {

        private lateinit var notification: NotificationOrder

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            notification = NotificationOrder()

            findPreference<SwitchPreference>(getString(R.string.notification_key))
                ?.setOnPreferenceChangeListener { _, newValue ->
                    newValue?.let {
                        when(it as Boolean) {
                            true ->  {
                                notification.setRemainderOrder(requireContext())
                            }
                            false -> {
                                notification.cancelRemainderOrder(requireContext())
                            }
                        }
                    }
                    true
                }
        }

    }
}
