package me.kavishdevar.ipadbattery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class IpadBatteryUpdateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "RYAN_IPAD_DATA_ARRIVED") {
            Log.d("IPAD_DEBUG", "1. Receiver successfully caught the MacroDroid intent!")

            val buds = intent.getIntExtra("buds", -1)
            val caseBattery = intent.getIntExtra("case", -1)
            val caseCharging = intent.getBooleanExtra("case_charging", false)

            Log.d("IPAD_DEBUG", "2. Data extracted -> Buds: $buds | Case: $caseBattery | Charging: $caseCharging")

            IpadBatteryStorage.saveBatteryLevels(context, buds, caseBattery, caseCharging)
            Log.d("IPAD_DEBUG", "3. Data saved to local storage.")

            val updateIntent = Intent(context, me.kavishdevar.librepods.widgets.BatteryWidget::class.java)
            updateIntent.action = "ME_KAVISHDEVAR_IPAD_BATTERY_UPDATED"
            context.sendBroadcast(updateIntent)
            Log.d("IPAD_DEBUG", "4. Sent broadcast to wake up the widget.")
        }
    }
}
