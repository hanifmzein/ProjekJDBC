package action

import model.Patient
import java.sql.{Connection,DriverManager}

import example.Query
import example.Conn

object PatientDAO {

  def showData(){

    val query = "SELECT * FROM patient"
    val rs = Query.read(query)

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

    Conn.connection.close
  }
   
  def addData( patient:Patient) = {

    val city = patient.city_id
    val nama = patient.patient_name
    val gender = patient.gender
    val date = patient.date_birth
    val address = patient.address

    val query = s"INSERT INTO `patient`(`patient_name`,`city_id`, `gender`, `date_birth`, `address`) " +
              s"VALUES ('$nama','$city','$gender','$date','$address')"

    Query.execute(query)
    Conn.connection.close
  }

  def updateData( patient:Patient) = {

    val id = patient.patient_id
    val city = patient.city_id
    val nama = patient.patient_name
    val gender = patient.gender
    val date = patient.date_birth
    val address = patient.address

    val query = s"UPDATE `patient` SET `patient_name` = '$nama',`city_id` = $city, `gender` = '$gender', `date_birth` = '$date', `address` = '$address' " +
      s"WHERE `patient`.`patient_id` = $id"

    Query.execute(query)
    Conn.connection.close
  }

  def deleteData(id:Int) = {

    val query = s"DELETE FROM `patient` WHERE `patient`.`patient_id` = $id"
    Query.execute(query)
    Conn.connection.close

  }

}