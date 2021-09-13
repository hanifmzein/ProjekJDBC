package example

import java.sql.Connection

class Conn {
    val url = "jdbc:mysql://localhost:3306/rs_db?serverTimezone=Asia/Jakarta"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = ""
    var connection:Connection = _
}