package uz.itschool.plants
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MyShared {



    companion object {
        private var mySharedPreference = MySharedPreference()
        private var handyBook2: SharedPreferences? = null
        private lateinit var editor: SharedPreferences.Editor
        private var gson = Gson()


        fun getInstance(context: Context): MySharedPreference {
            if (handyBook2 == null) {
                handyBook2= context.getSharedPreferences("handyBook2", Context.MODE_PRIVATE)
            }
            return mySharedPreference
        }

    }


    fun setStatus(status: Boolean) {
        editor = handyBook2!!.edit()
        editor.putBoolean("status", status).apply()
    }
//fun getOrder():MutableList<Plant>{
//    var bookList= mutableListOf<Plant>()
//    val bookStringList = handyBook2!!.getString("book","")
//    val type = object : TypeToken<List<Plant>>(){}.type
//    if (bookStringList!=""){
//        bookList = gson.fromJson(bookStringList, type)
//    }
//    return bookList
//}



    fun getOrder(): MutableList<uz.itschool.plants.Object> {
        var bookList= mutableListOf<uz.itschool.plants.Object>()
        val bookStringList = handyBook2!!.getString("book","")
        val type = object : TypeToken<List<uz.itschool.plants.Object>>(){}.type
        if (bookStringList!=""){
            bookList = gson.fromJson(bookStringList, type)
        }
        return bookList
    }
//    fun addOrder(plant:Plant){
//        var bookList= mutableListOf<Plant>()
//        val bookStringList = handyBook2!!.getString("book","")
//        val type = object : TypeToken<List<Plant>>(){}.type
//        if (bookStringList!=""){
//            bookList = MySharedPreference.gson.fromJson(bookStringList, type)
//            bookList.add(plant)
//        }
//        else{
//            bookList = mutableListOf()
//        }
//        MySharedPreference.editor = MySharedPreference.handyBook2!!.edit()
//        MySharedPreference.editor.putString("plantt", MySharedPreference.gson.toJson(plantCartList))
//        MySharedPreference.editor.apply()
//    }


}