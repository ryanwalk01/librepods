package me.kavishdevar.ipadbattery

import android.content.Context
import android.content.SharedPreferences

object IpadBatteryStorage {
    private const val PREFS_NAME = "IpadBatterySyncPrefs"

    // Added caseCharging boolean
    fun saveBatteryLevels(context: Context, buds: Int, caseBattery: Int, caseCharging: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putInt("buds_battery", buds)
            putInt("case_battery", caseBattery)
            putBoolean("case_charging", caseCharging) // Save the charging state
            apply()
        }
    }

    // Updated data class
    data class IpadBatteryData(val buds: Int, val caseBattery: Int, val caseCharging: Boolean)

    fun getBatteryLevels(context: Context): IpadBatteryData {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return IpadBatteryData(
            buds = prefs.getInt("buds_battery", -1),
            caseBattery = prefs.getInt("case_battery", -1),
            caseCharging = prefs.getBoolean("case_charging", false) // Default to false
        )
    }
}
