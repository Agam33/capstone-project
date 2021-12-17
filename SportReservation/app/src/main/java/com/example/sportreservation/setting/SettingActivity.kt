package com.example.sportreservation.setting

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.sportreservation.R
import com.example.sportreservation.notification.NotificationOrder

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.txt_setting)
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        private lateinit var notification: NotificationOrder

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            notification = NotificationOrder()

            findPreference<SwitchPreference>(getString(R.string.notification_key))
                ?.setOnPreferenceChangeListener { _, newValue ->
                    newValue?.let {
                        val isRemainder = it as Boolean
                        if (isRemainder) {
                            notification.setRemainderOrder(requireContext())
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.notification_enable),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            notification.cancelRemainderOrder(requireContext())
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.notification_disable),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    true
                }
        }
    }
}
