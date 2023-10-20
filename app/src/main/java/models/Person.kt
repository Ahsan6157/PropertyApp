package models


import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("Person_table")
data class Person(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="userId")
    val id: Int,
   @ColumnInfo(name="name")
    val name: String?,
    @ColumnInfo(name="email")
    val email: String?,
    @ColumnInfo(name="phoneNumber")
    val phoneNumber: String?,
    @ColumnInfo(name="password")
    val pass: String?,
)

