package models
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("property_address")
data class PropertyAddress(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "propertyId")
    val propertyId:Int,
    @ColumnInfo(name = "userEmail")
    val userEmail: String?,
//    property name
    @ColumnInfo(name = "property name")
    val propertyName: String?,
    @ColumnInfo(name = "propertyAddress")
    val propertyAdd: String?,
    @ColumnInfo(name = "city")
    val city: String?,
//size
    @ColumnInfo(name = "size")
    val size: String?,

//    KITCHENS
    @ColumnInfo(name = "kitchens")
    val kitchen: String,
//  number of rooms
    @ColumnInfo(name = "rooms")
    val rooms: String?,
//    washrooms
    @ColumnInfo(name = "washrooms")
    val washrooms: String?,
//    furnihed/non furnished
    @ColumnInfo(name = "Furnished")
    val furnished: String?,
//    floors
    @ColumnInfo(name = "floors")
    val floors: String?,
//    sale/rent
    @ColumnInfo(name = "sale/rent")
    val sale: String?,
//price
    @ColumnInfo(name = "price")
    val price: String?,
    )
