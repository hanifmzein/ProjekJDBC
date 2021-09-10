package example

import example.Conn
import java.sql.{Connection,DriverManager}
import java.sql.ResultSet

object Query{

    def read (query : String) : ResultSet = {
        try {

            Class.forName(Conn.driver)
            Conn.connection = DriverManager.getConnection(Conn.url, Conn.username, Conn.password)
            val statement = Conn.connection.createStatement

            val result = statement.executeQuery(query)

            // Conn.connection.close
            
            return result


        } catch {
            case e: Exception => e.printStackTrace
            return null
        }

    }

    def execute (query : String) = {
        try {

            Class.forName(Conn.driver)
            Conn.connection = DriverManager.getConnection(Conn.url, Conn.username, Conn.password)
            val statement = Conn.connection.createStatement

            val result = statement.executeUpdate(query)

            Conn.connection.close

        } catch {
            case e: Exception => e.printStackTrace
        }

    }

}