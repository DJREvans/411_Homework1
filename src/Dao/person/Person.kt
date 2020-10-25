package cpsc411.Dao.person

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Person (var firstName:String?, var lastName:String?, var ssn:String?)

fun main() {
    //JSON Serialization
    val pObj = Person("George", "Sampson", "64587154")
    val jsonStr = Gson().toJson(pObj)
    println("The converted JSON string : ${jsonStr}")

    //Serialization of List<Person>
    var pList : MutableList<Person> = mutableListOf()
    pList.add(pObj)
    pList.add(Person("Amie", "lam", "6452545248"))
    val listJsonString = Gson().toJson(pList)
    //JSONArray object
    println("${listJsonString}")

    //List<Person> object deserialization
    val personListType: Type = object: TypeToken<Person>(){}.type


    //JSON Deserialization
    var pObj1 : Person
    pObj1 = Gson().fromJson(jsonStr, Person::class.java)
    println("${pObj1.toString()}")

}