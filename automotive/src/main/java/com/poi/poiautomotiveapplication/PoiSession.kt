package com.poi.poiautomotiveapplication
import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session

class PoiSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return PoiScreen(carContext)
    }

}
