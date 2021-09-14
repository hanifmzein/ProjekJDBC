package example

import java.io.FileInputStream
import java.sql.Connection
import java.util.Properties

class Conn {
    var connection:Connection = _

    val input = new FileInputStream("config.properties")
    val properties = new Properties();

    // load a properties file
    properties.load(input);

    val host: String = properties.getProperty("host")
    val driver: String = properties.getProperty("driver")
    val username: String = properties.getProperty("username")
    val password: String = properties.getProperty("password")

}