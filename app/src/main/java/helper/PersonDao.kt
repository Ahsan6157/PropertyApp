package helper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import models.Person

@Dao
interface PersonDao {

    @Insert()
    suspend fun insert(list: List<Person> )

    @Update()
    suspend fun update(person: Person)

    @Delete()
    suspend fun delete(person: Person)
@Query("select * from person_table where email=:email")
    fun getUserEmail(email: String):Person

    @Query("select * from person_table order by userId")
       fun getAllUser(): LiveData<List<Person>>
      @Query("select * from person_table where email= :email and password= :pass")
       suspend fun getUser(email:String,pass:String):Person
}