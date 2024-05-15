package com.poi.poiautomotiveapplication

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.MessageTemplate
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import data.PlacesRepository

class DetailsScreen(carContext: CarContext, private val placeId: Int) : Screen(carContext) {
    override fun onGetTemplate(): Template {

        val place = PlacesRepository().getPlace(placeId) ?:
        return MessageTemplate.Builder("Place not Found")
            .setHeaderAction(Action.BACK)
            .build()

        return PaneTemplate.Builder(
            Pane.Builder()
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
            .build()

    }

}