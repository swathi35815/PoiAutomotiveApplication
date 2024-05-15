package data.model

import android.content.Intent
import androidx.core.net.toUri

data class Place(
    var id : Int,
    var location_name : String,
    var latitude : Double,
    var longitude : Double,
    var description : String,
    var distance : Float,
    var address : String,
    var image : String,
    var hours : Float,
    var ticket : Float
)

fun Place.toIntent(action : String) : Intent {
    return Intent(action).apply {
        data = "geo:${latitude},${longitude}".toUri()
    }
}
