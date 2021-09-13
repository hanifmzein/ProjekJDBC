package example

import java.sql.{DriverManager, PreparedStatement, ResultSet, Statement}
import example.Conn

class Query{

    val conn = new Conn

    def statement: Statement = {

        try {
            Class.forName(conn.driver)
            conn.connection = DriverManager.getConnection(conn.host, conn.username, conn.password)
            val st:Statement = conn.connection.createStatement
            return st
        } catch {
            case e: Exception => e.printStackTrace
                return null
        }
    }

    def read (query : String) : ResultSet = {
        try {
            val result = statement.executeQuery(query)
//            conn.connection.close
            return result
        } catch {
            case e: Exception => e.printStackTrace
            return null
        }
    }

    def execute (query : String) = {
        try {
            statement.executeUpdate(query)

            conn.connection.close

        } catch {
            case e: Exception => e.printStackTrace
        }

    }

    def close: Unit = {
        conn.connection.close()
    }

}