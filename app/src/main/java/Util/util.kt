package Util

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class util {
    companion object{
        fun openActivity(context: Context, objClass: Class<*>){
            val intent = Intent(context, objClass)
            startActivity(context, intent, null)
        }
    }
}