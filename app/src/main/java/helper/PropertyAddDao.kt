package helper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import models.PropertyAddress

@Dao
interface PropertyAddDao {
    //    insert
    @Insert()
    fun insertProperty(address: PropertyAddress)

    //    get all property
    @Query("select * from property_address")
    fun getAllProperty(): LiveData<List<PropertyAddress>>

    //    update property
    @Update()
    suspend fun updateProperty(property: PropertyAddress)
//    delete property
    @Delete()
    suspend fun deleteProperty(property: PropertyAddress)

    @Query("select * from property_address where size=:size")
      fun filteredData( size:String):LiveData<List<PropertyAddress>>


}