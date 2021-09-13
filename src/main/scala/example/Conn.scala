package example

import java.net.URL
import java.sql.Connection
import java.util.Properties
import scala.io.Source
import java.io.FileInputStream

class Conn {
    var connection:Connection = _
    val properties = new Properties()
    val in = new FileInputStream("application.properties")
    properties.load(in)

    val host: String = properties.getProperty("host")
    val driver: String = properties.getProperty("driver")
    val username: String = properties.getProperty("username")
    val password: String = properties.getProperty("password")


}