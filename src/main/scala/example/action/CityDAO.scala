package action

import model.City
import java.sql.{Connection,DriverManager}

import example.Query
import example.Conn

object CityDAO {

  val url = "jdbc:mysql://localhost:3306/rs_db?serverTimezone=Asia/Jakarta"
  val driver = "com.mysql.cj.jdbc.Driver"
  val username = "root"
  val password = ""
  var connection:Connection = _

  def showData(){

    val query = "SELECT * FROM city"
    val rs = Query.read(query)

    while (rs.next) {
      val id = rs.getString("city_id")
      val name = rs.getString("city_name")

      println(s"City #$id\nNama : $name")
    }

    Conn.connection.close
  }
   
  def addData( city:City ) = {

    val nama = city.city_name
    val query = s"INSERT INTO  city (city_name) VALUES ('$nama')"
    Query.execute(query)
  }

  def updateData( city:City) = {

    val id = city.city_id
    val nama = city.city_name

    val query = s"UPDATE  city SET city_name = '$nama'" +
      s"WHERE  city. city_id = $id"

    Query.execute(query)
  }

  def deleteData(id:Int) = {

    val query = s"DELETE FROM  city` WHERE  city`. city_id` = $id"
    Query.execute(query)
  }

}