package action

import model.City
import java.sql.{Connection,DriverManager}

object CityDAO {

  val url = "jdbc:mysql://localhost:3306/rs_db?serverTimezone=Asia/Jakarta"
  val driver = "com.mysql.cj.jdbc.Driver"
  val username = "root"
  val password = ""
  var connection:Connection = _

  def showData(){

    try {
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)
        val statement = connection.createStatement

        val query = "SELECT * FROM city"
        val rs = statement.executeQuery(query)

        while (rs.next) {
          val id = rs.getString("city_id")
          val name = rs.getString("city_name")

          println(s"City #$id\nNama : $name")
        }

        connection.close
        } catch {
        case e: Exception => e.printStackTrace
    }
  }
   
  def addData( city:City ) = {

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement

      val nama = city.city_name

      val query = s"INSERT INTO  city`( city_name`) " +
                s"VALUES ('$nama')"
      
      val result = statement.executeUpdate(query)

      connection.close

      // return result

    } catch {
        
      case e: Exception => e.printStackTrace
    }
  }

  def updateData( city:City) = {

    try {
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)
        val statement = connection.createStatement

        val id = city.city_id
        val nama = city.city_name

        val query = s"UPDATE  city` SET  city_name` = '$nama'" +
          s"WHERE  city`. city_id` = $id"

        val result = statement.executeUpdate(query)

        connection.close

        // return result
        } catch {
        case e: Exception => e.printStackTrace
    }
  }

  def deleteData(id:Int) = {

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement

      val query = s"DELETE FROM  city` WHERE  city`. city_id` = $id"

      val result = statement.executeUpdate(query)

      connection.close

      // return result

      } catch {
      case e: Exception => e.printStackTrace
    }
  }

}