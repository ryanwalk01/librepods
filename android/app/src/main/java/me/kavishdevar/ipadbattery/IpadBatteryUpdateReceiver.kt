package me.kavishdevar.ipadbattery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class IpadBatteryUpdateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // This is the custom password MacroDroid will shout
        if (intent.action == "RYAN_IPAD_DATA_ARRIVED") {

            // Extract the data MacroDroid attached to the intent
            val buds = intent.getIntExtra("buds", -1)
            val caseBattery = intent.getIntExtra("case", -1)
            val caseCharging = intent.getBooleanExtra("case_charging", false)

            // Save the data locally so the widget can remember it
            IpadBatteryStorage.saveBatteryLevels(context, buds, caseBattery, caseCharging)

            // Shout the original intent so LibrePods knows to redraw the widget right now!
            val updateIntent = Intent("ME_KAVISHDEVAR_IPAD_BATTERY_UPDATED")
            updateIntent.setPackage(context.packageName)
            context.sendBroadcast(updateIntent)
        }
    }
}
