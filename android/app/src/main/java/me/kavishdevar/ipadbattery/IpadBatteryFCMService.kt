package me.kavishdevar.ipadbattery

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class IpadBatteryFCMService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val data = remoteMessage.data
        if (data.isNotEmpty()) {
            val buds = data["buds"]?.toIntOrNull() ?: -1
            val caseBattery = data["case"]?.toIntOrNull() ?: -1

            // Translate "Yes" to true, anything else to false
            val caseChargingRaw = data["case_charging"] ?: "No"
            val caseCharging = caseChargingRaw.equals("Yes", ignoreCase = true)

            // Save the new numbers AND the charging state
            IpadBatteryStorage.saveBatteryLevels(applicationContext, buds, caseBattery, caseCharging)

            // Shout into the Android system
            val updateIntent = Intent("ME_KAVISHDEVAR_IPAD_BATTERY_UPDATED")
            sendBroadcast(updateIntent)
        }
    }
}
