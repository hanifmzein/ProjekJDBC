package example.action

import example.Query
import example.Conn
import example.model.City

class CityDAO {
  val query = new Query
  val conn = new Conn

  def getData(): List[City] = {

    var cityList:List[City] = List()

    val squery = "SELECT * FROM city"
    val rs = query.read(squery)

    while (rs.next) {
      val id = rs.getString("city_id").toInt
      val name = rs.getString("city_name")

      val cityBaru = City(id, name)::Nil
      cityList = cityList ::: cityBaru
    }

    query.close

    return cityList

  }
   
  def addData( city:City ) = {

    val nama = city.city_name
    val squery = s"INSERT INTO  city (city_name) VALUES ('$nama')"
    query.execute(squery)
  }

  def updateData( city:City) = {

    val id = city.city_id
    val nama = city.city_name

    val squery = s"UPDATE  city SET city_name = '$nama' WHERE  city. city_id = $id"


    query.execute(squery)
  }

  def deleteData(id:Int) = {

    val squery = s"DELETE FROM city WHERE `city_id` = $id"
    query.execute(squery)
  }

  def getItem(id:Int) : City = {
    val squery = s"SELECT * FROM `city` WHERE `city_id` = $id"

    val row = query.read(squery)

    row.next()
    val name = row.getString("city_name")
    val city = City(id, name)

    city
  }

}