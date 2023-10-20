package adapter

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope

class SharedPrefs(context: Context) {
    private val prefs: SharedPreferences=  context.getSharedPreferences("userData", AppCompatActivity.MODE_PRIVATE)
     private val editor:Editor = prefs.edit()
   fun setString(key:String,text:String){
        editor.putString(key,text)
       editor.apply()
       editor.commit()
    }

    fun setBoolean(key:String,flag:Boolean){
        editor.putBoolean(key,flag)
        editor.apply()
        editor.commit()
    }

    fun remove(key:String){
        editor.remove(key)
        editor.apply()
        editor.commit()
    }

    fun getStringValue(key:String,defualtValue:String):String{
       return prefs.getString(key,defualtValue).toString()
    }
}