package cpsc411.Dao.person
import cpsc411.Dao.Dao
import cpsc411.Dao.Database

class PersonDao : Dao() {
    fun addPerson(pObj : Person){
        //get db connection
        val conn = Database.getInstance()?.getDbConnection()

        //prepare the sql statement
        sqlStmt = "insert into person (first_name, last_name, ssn) values ('${pObj.firstName}', '${pObj.lastName}', '${pObj.ssn}')"

        //submit the sql statement
        conn?.exec(sqlStmt)
    }
    fun getAll() : List<Person> {
        //get db connection
        val conn = Database.getInstance()?.getDbConnection()

        //prepare the sql statement
        sqlStmt = "select first_name, last_name, ssn from person"

        //submit the sql statement
        var personList : MutableList<Person> = mutableListOf()
        val st = conn?.prepare(sqlStmt)

        // Convert into Kotlin object format
        while(st?.step()!!){
            val fn = st.columnString(0)
            val ln = st.columnString(1)
            val ssn = st.columnString(2)
            personList.add(Person(fn, ln, ssn))
        }
        return personList
    }

}