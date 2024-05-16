package com.poi.poiautomotiveapplication

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.ActionStrip
import androidx.car.app.model.CarColor
import androidx.car.app.model.CarIcon
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Place
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.drawable.IconCompat
import com.example.poiautomotiveapplication.R
import data.PlacesRepository
import data.model.toIntent

class DetailsScreen(carContext: CarContext, private val placeId: Int) : Screen(carContext) {
    private var isFavourite = false

    override fun onGetTemplate(): Template {

        val place = PlacesRepository().getPlace(placeId) ?:
        return MessageTemplate.Builder("Place not Found")
            .setHeaderAction(Action.BACK)
            .build()

        val navigateAction = Action.Builder()
            .setTitle("Navigate")
            .setOnClickListener {
                carContext.startCarApp(place.toIntent(CarContext.ACTION_NAVIGATE))
            }
            .build()

        val actionStrip = ActionStrip.Builder()
            .addAction(
                Action.Builder()
                    .setIcon(
                        CarIcon.Builder(
                            IconCompat.createWithResource(carContext, R.drawable.baseline_favorite_24)
                                .setTint(
                                    ColorStateList.valueOf(Color.RED).getDefaultColor()
                                    //if(isFavourite) CarColor.RED else CarColor.GREEN
                                )

                        )
                            .build()
                    ).setOnClickListener {
                        isFavourite = !isFavourite
                        invalidate()
                        Log.i("mytag", "isFavourite = $isFavourite")
                    }
                    .build()
            )
            .build()

        return PaneTemplate.Builder(
            Pane.Builder()
                .addAction(navigateAction)
                .addRow(
                    Row.Builder()
                        .setTitle("Coordinates")
                        .addText("${place.latitude}, ${place.longitude}")
                        .build()
                )
                .addRow(
                    Row.Builder()
                        .setTitle("Description")
                        .addText(place.description)
                        .build()
                )
                .build()
        ).setTitle(place.location_name)
            .setHeaderAction(Action.BACK)
            .setActionStrip(actionStrip)
            .build()

    }

}
