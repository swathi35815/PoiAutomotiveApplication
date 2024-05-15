package com.poi.poiautomotiveapplication

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.ScreenManager
import androidx.car.app.model.CarLocation
import androidx.car.app.model.Distance
import androidx.car.app.model.DistanceSpan
import androidx.car.app.model.ItemList
import androidx.car.app.model.Metadata
import androidx.car.app.model.Place
import androidx.car.app.model.PlaceListMapTemplate
import androidx.car.app.model.PlaceMarker
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import data.PlacesRepository


class PoiScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {

        /*CarToast.makeText(carContext, "Hello!", CarToast.LENGTH_LONG).show()

        // Pane Template
        val row1 = Row.Builder().setTitle("Hello World!").build()
        val pane = Pane.Builder().addRow(row1).build()
        return PaneTemplate.Builder(pane)
           .setHeaderAction(Action.BACK)
           .build()*/

        val placesRepository = PlacesRepository()
        val itemListBuilder = ItemList.Builder().setNoItemsMessage("No Places Found")

        placesRepository.getPlaces().forEach {
            itemListBuilder.addItem(
                Row.Builder()
                    .setTitle(it.location_name)
                    .addText(SpannableString(" ").apply {
                        setSpan(DistanceSpan.create(
                            Distance.create(it.distance.toDouble(), Distance.UNIT_KILOMETERS)),
                            0,
                            1,
                            Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                    })
                    .setOnClickListener {
                        screenManager.push(DetailsScreen(carContext, it.id))
                    }
                    .setMetadata(
                        Metadata.Builder().setPlace(
                            Place.Builder(CarLocation.create(it.latitude, it.longitude))
                                .setMarker(PlaceMarker.Builder().build())
                                .build()
                        ).build()
                    )
                    .build()
            )
        }

        return PlaceListMapTemplate.Builder()
            .setTitle("Places of Interest")
            .setItemList(itemListBuilder.build())
            .build()
    }

}
