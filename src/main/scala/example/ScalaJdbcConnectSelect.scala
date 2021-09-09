package example

import java.sql.{Connection,DriverManager}
import scala.io.StdIn.readLine
import java.sql.Date

object ScalaJdbcConnectSelect extends App {

  // connect to the database named "mysql" on port 8889 of localhost
//   val url = s"jdbc:mysql://localhost:3306/${db}?serverTimezone=UTC"
//       val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/rs_db?serverTimezone=Asia/Jakarta"
  val driver = "com.mysql.cj.jdbc.Driver"
  val username = "root"
  val password = ""
  var connection:Connection = _
  try {

    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val statement = connection.createStatement

    def addData( patient:Patient) : Int = {

      val nama = patient.patient_name
      val gender = patient.gender
      val date = patient.date_birth
      val address = patient.address

      val query = s"INSERT INTO `patient`(`patient_name`, `gender`, `date_birth`, `address`) VALUES ('$nama','$gender','$date','$address')"
      val result:Int = statement.executeUpdate(query)

      return result
    }

    def updateData( patient:Patient) : Int = {

      val id = patient.patient_id
      val nama = patient.patient_name
      val gender = patient.gender
      val date = patient.date_birth
      val address = patient.address

      val query = s"UPDATE `patient` SET `patient_name` = '$nama', `gender` = '$gender', `date_birth` = '$date', `address` = '$address' WHERE `patient`.`patient_id` = $id"
      println("QUERY : "+query)
      val result:Int = statement.executeUpdate(query)

      return result
    }

    def deleteData(id:Int) : Int = {

      val query = s"DELETE FROM `patient` WHERE `patient`.`patient_id` = $id"
      val result:Int = statement.executeUpdate(query)

      return result
    }

    case class Patient(patient_id: Int , patient_name: String, 
      gender: Char, date_birth: String, address: String)

    var l = true
    while (l){

      println("\n\nDaftar Pasien RS\n-------------")
      val rs = statement.executeQuery("SELECT * FROM patient")
      while (rs.next) {
        val id = rs.getString("patient_id")
        val name = rs.getString("patient_name")
        val gender = rs.getString("gender")
        val birth = rs.getString("date_birth")
        val address = rs.getString("address")
        println(s"Pasien #$id\nNama : $name\nGender : $gender\nLahir : $birth\nAlamat : $address\n")
      }

      println("-------------\n" +
        "1. Tambah Data\n" +
        "2. Ganti Data\n" +
        "3. Hapus Data\n" +
        "\n0. Exit\n\n")
      val pilih = readLine("pilih : ")
      if (pilih == "1"){
        val i_name = readLine("Masukan Nama : ")
        val i_gender = readLine("Masukan gender (l/p) : ").toCharArray()(0)
        val i_date = readLine("Masukan date (yyyy-mm-dd) : ")
        val i_address = readLine("Masukan Alamat : ")

        val t_patient = Patient(0, i_name, i_gender, i_date, i_address)

        addData(t_patient)

      } else if (pilih == "2"){

        val i_id = readLine("Masukan id : ").toInt
        val i_name = readLine("Masukan Nama : ")
        val i_gender = readLine("Masukan gender (l/p) : ").toCharArray()(0)
        val i_date = readLine("Masukan date (yyyy-mm-dd) : ")
        val i_address = readLine("Masukan Alamat : ")

        val t_patient = Patient(i_id, i_name, i_gender, i_date, i_address)

        updateData(t_patient)
        

      } else if (pilih == "3"){

        val id = readLine("Masukan Id : ").toInt
        deleteData(id)

      } else if (pilih=="0") {
        l = false
      }
    }

    connection.close
  } catch {
    case e: Exception => e.printStackTrace
  }

}
