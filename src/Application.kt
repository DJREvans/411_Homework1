//890422637
//Daniel Evangelista

package cpsc411

import com.google.gson.Gson
import cpsc411.Dao.Database
import cpsc411.Dao.claim.Claim
import cpsc411.Dao.claim.ClaimDao
import cpsc411.Dao.person.Person
import cpsc411.Dao.person.PersonDao
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.utils.io.*
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    //extension
    //@annotation
    //routing constructor takes only one parameter which is a lambda function
    //DSL - Domain Specific Language
    routing{
//        this.get("/PersonService/add"){
//            println("HTTP msg is using GET method with /get")
//            val fn = call.request.queryParameters["FirstName"]
//            val ln : String? = call.request.queryParameters["LastName"]
//            val sn : String? = call.request.queryParameters["SSN"]
//            val response = String.format("First  Name is %s and Last Name %s", fn, ln)
//            //
//            val pObj = Person(fn, ln, sn)
//            val dao = PersonDao().addPerson(pObj)
//            call.respondText(response, status= HttpStatusCode.OK, contentType = ContentType.Text.Plain)
//        }
//        get("PersonService/getAll"){
//            val pList = PersonDao().getAll()
//            println("The number of students : ${pList.size}")
//            //JSON Serialization/Deserialization
//            val respJsonStr = Gson().toJson(pList)
//            call.respondText(respJsonStr, status=HttpStatusCode.OK, contentType = ContentType.Application.Json)
//
//            call.respondText ( "Get All service was processed. ", status= HttpStatusCode.OK, contentType = ContentType.Text.Plain )
//
//        }
        //////////////////////////////////////////////////////////////////////////////////////////////
        get("ClaimService/getAll"){
            val cList = ClaimDao().getAll()
            println("The number of claims : ${cList.size}")
            val respJsonStr = Gson().toJson(cList)
            call.respondText(respJsonStr, status=HttpStatusCode.OK, contentType = ContentType.Application.Json)

            call.respondText ( "Get All service was processed. ", status= HttpStatusCode.OK, contentType = ContentType.Text.Plain )
        }
        //////////////////////////////////////////////////////////////////////////////////////////////
//        post("/PersonService/adc"){
//            val contType = call.request.contentType()
//            val data = call.request.receiveChannel()
//            val dataLength = data.availableForRead
//            var output = ByteArray(dataLength)
//            data.readAvailable(output)
//            val str = String(output) // for further processing
//
//            println("HTTP msg is using POST method with /post ${contType} ${str}")
//            call.respondText("The POST request was successfully processed", status= HttpStatusCode.OK, contentType = ContentType.Text.Plain)
//        }
        /////////////////////////////////////////////////////////////////////////////////////////////////
        post("/ClaimService/add"){
            val contType = call.request.contentType()
            val data = call.request.receiveChannel()
            val dataLength = data.availableForRead
            var output = ByteArray(dataLength)
            data.readAvailable(output)
            val str = String(output) // for further processing

            //JSON Deserialization
            val gsonStr = Gson().fromJson(str, Claim::class.java)
            val claim = Claim(UUID.randomUUID(), gsonStr.title, gsonStr.date,isSolved=false)
            ClaimDao().addClaim(claim)

            println("HTTP msg is using POST method with /post ${contType} ${str}")
            call.respondText("The POST request was successfully processed", status= HttpStatusCode.OK, contentType = ContentType.Text.Plain)
        }


    }

}

