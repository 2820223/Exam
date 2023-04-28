package uz.itschool.plants
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SHaredPreference {
    companion object {
        private var mySharedPreference = SHaredPreference()
        private var handyBook: SharedPreferences? = null
        private lateinit var editor: SharedPreferences.Editor
        private var gson = Gson()
        fun getInstance(context: Context): SHaredPreference {
            if (handyBook == null) {
                handyBook = context.getSharedPreferences("handyBook", Context.MODE_PRIVATE)
            }
            return mySharedPreference
        }
    }

    fun getStatus(): Boolean {
        return handyBook!!.getBoolean("status", false)
    }

    fun setStatus(status: Boolean) {
        editor = handyBook!!.edit()
        editor.putBoolean("status", status).apply()
    }

    fun getPlant():MutableList<uz.itschool.plants.Object>{
        var plantCartList= mutableListOf<uz.itschool.plants.Object>()
        val plantStringList = handyBook!!.getString("plantt","")
        val type = object : TypeToken<List<uz.itschool.plants.Object>>(){}.type
        if (plantStringList!=""){
            plantCartList = gson.fromJson(plantStringList, type)
        }
        return plantCartList
    }



    fun clearLIst(){
        val plantCartList = mutableListOf<uz.itschool.plants.Object>()
        editor= handyBook!!.edit()
        editor.putString("plantt",gson.toJson(plantCartList))

        editor.apply()

    }

    fun addPlant(plant: uz.itschool.plants.Object){
        var plantCartList:MutableList<uz.itschool.plants.Object>
        val plantStringList = handyBook!!.getString("plantt","")
        val type = object : TypeToken<List<uz.itschool.plants.Object>>(){}.type
        if (plantStringList!=""){
            plantCartList = gson.fromJson(plantStringList, type)
            plantCartList.add(plant)
        }
        else{
            plantCartList = mutableListOf()
        }
        editor= handyBook!!.edit()
        editor.putString("plantt",gson.toJson(plantCartList))
        editor.apply()
    }

    fun removePlant(plant: uz.itschool.plants.Object){
        var plantCartList= mutableListOf<uz.itschool.plants.Object>()
        val plantStringList = handyBook!!.getString("plantt","")
        val type = object : TypeToken<List<uz.itschool.plants.Object>>(){}.type
        if (plantStringList!="") {
            plantCartList = gson.fromJson(plantStringList, type)
            Log.d("TAG", "removePlant: ${plant.name}")
            for (i in plantCartList.indices) {
                Log.d("TAG", "$i -->   ${plant.name}")
            }
            plantCartList.remove(plant)
        }
        editor= handyBook!!.edit()
        editor.putString("plantt",gson.toJson(plantCartList))
        editor.apply()
    }

    fun getOrder():MutableList<uz.itschool.plants.Object>{
        var plantOrderList= mutableListOf<uz.itschool.plants.Object>()
        val plantStringListOr = handyBook!!.getString("plantOrder","")
        val type = object : TypeToken<List<uz.itschool.plants.Object>>(){}.type
        if (plantStringListOr!=""){
            plantOrderList=  gson.fromJson(plantStringListOr, type)
        }
        return plantOrderList
    }

    fun addOrder(plist:MutableList<uz.itschool.plants.Object>){
        editor= handyBook!!.edit()
        editor.putString("plantOrder",gson.toJson(plist))
        editor.apply()
    }


}