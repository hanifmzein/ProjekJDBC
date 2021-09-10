package action

import model.Patient
import java.sql.{Connection,DriverManager}

object PatientDAO {

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

        val query = "SELECT * FROM patient"
        val rs = statement.executeQuery(query)

        while (rs.next) {
          val id = rs.getString("patient_id")
          val city_id = rs.getString("city_id")
          val name = rs.getString("patient_name")
          val gender = rs.getString("gender")
          val birth = rs.getString("date_birth")
          val address = rs.getString("address")

          // //get City_name from city_id
          // val query_city = s"SELECT * FROM `city` WHERE `city_id` = $city_id"
          // println("query : "+query_city)

          // val rs_city = statement.executeQuery(query_city)
          // rs_city.next
          // val city = rs_city.getString("city_name")

          println(s"Pasien #$id\nNama : $name\nCity : $city_id\nGender : $gender\nLahir : $birth\nAlamat : $address\n")
        }

        connection.close
        } catch {
        case e: Exception => e.printStackTrace
    }
  }
   
  def addData( patient:Patient) = {

    try {
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
      val statement = connection.createStatement

      val city = patient.city_id
      val nama = patient.patient_name
      val gender = patient.gender
      val date = patient.date_birth
      val address = patient.address

      val query = s"INSERT INTO `patient`(`patient_name`,`city_id`, `gender`, `date_birth`, `address`) " +
                s"VALUES ('$nama','$city','$gender','$date','$address')"
      
      val result = statement.executeUpdate(query)

      connection.close

      // return result

    } catch {
        
      case e: Exception => e.printStackTrace
    }
  }

  def updateData( patient:Patient) = {

    try {
        Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)
        val statement = connection.createStatement

        val id = patient.patient_id
        val city = patient.city_id
        val nama = patient.patient_name
        val gender = patient.gender
        val date = patient.date_birth
        val address = patient.address

        val query = s"UPDATE `patient` SET `patient_name` = '$nama',`city_id` = $city, `gender` = '$gender', `date_birth` = '$date', `address` = '$address' " +
          s"WHERE `patient`.`patient_id` = $id"

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

      val query = s"DELETE FROM `patient` WHERE `patient`.`patient_id` = $id"

      val result = statement.executeUpdate(query)

      connection.close

      // return result

      } catch {
      case e: Exception => e.printStackTrace
    }
  }

}