package cpsc411.Dao.claim

import cpsc411.Dao.Dao
import cpsc411.Dao.Database
import java.util.*

class ClaimDao : Dao() {
    fun addClaim(cObj : Claim){
        //get db connection
        val conn = Database.getInstance()?.getDbConnection()

        //prepare the sql statement
        sqlStmt = "insert into Claim (id, title, date, isSolved) values ('${cObj.id}', '${cObj.title}', '${cObj.date}', '${cObj.isSolved}')"

        //submit the sql statement
        conn?.exec(sqlStmt)
    }
    //fun booleanToInt(b: Boolean) :Int { return if (b) 1 else 0 }

    fun getAll() : List<Claim> {
        //get db connection
        val conn = Database.getInstance()?.getDbConnection()

        //prepare the sql statement
        sqlStmt = "select id, title, date, isSolved from Claim"

        //submit the sql statement
        var claimList : MutableList<Claim> = mutableListOf()
        val st = conn?.prepare(sqlStmt)

        // Convert into Kotlin object format
        while(st?.step()!!){
            val id = st.columnString(0)
            val title = st.columnString(1)
            val date = st.columnString(2)
            val isSolved = st.columnNull(3) //add isSolved column
            claimList.add(Claim(UUID.fromString(id), title, date, isSolved))
        }
        return claimList
    }

}