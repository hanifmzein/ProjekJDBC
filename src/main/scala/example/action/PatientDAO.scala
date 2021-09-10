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
          val name = rs.getString("patient_name")
          val gender = rs.getString("gender")
          val birth = rs.getString("date_birth")
          val address = rs.getString("address")

          println(s"Pasien #$id\nNama : $name\nGender : $gender\nLahir : $birth\nAlamat : $address\n")
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

      val nama = patient.patient_name
      val gender = patient.gender
      val date = patient.date_birth
      val address = patient.address

      val query = s"INSERT INTO `patient`(`patient_name`, `gender`, `date_birth`, `address`) " +
                s"VALUES ('$nama','$gender','$date','$address')"
      
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
        val nama = patient.patient_name
        val gender = patient.gender
        val date = patient.date_birth
        val address = patient.address

        val query = s"UPDATE `patient` SET `patient_name` = '$nama', `gender` = '$gender', `date_birth` = '$date', `address` = '$address' " +
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