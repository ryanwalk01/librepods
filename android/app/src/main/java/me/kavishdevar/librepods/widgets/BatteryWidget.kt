/*
    LibrePods - AirPods liberated from Apple’s ecosystem
    Copyright (C) 2025 LibrePods contributors

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

@file:OptIn(ExperimentalEncodingApi::class)

package me.kavishdevar.librepods.widgets

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import me.kavishdevar.librepods.services.ServiceManager
import kotlin.io.encoding.ExperimentalEncodingApi
import android.content.Intent
import android.util.Log

class BatteryWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        ServiceManager.getService()?.updateBattery()
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent) // Critical: keeps native widget functions working

        // If the broadcast is from our iPad FCM service...
        if (intent.action == "ME_KAVISHDEVAR_IPAD_BATTERY_UPDATED") {
            Log.d("IPAD_DEBUG", "5. Widget caught the internal broadcast. Forcing UI redraw now!")
            ServiceManager.getService()?.updateBattery()
        }
    }
}
