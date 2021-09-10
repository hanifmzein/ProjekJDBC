package example

import java.sql.{Connection,DriverManager}
import scala.io.StdIn.readLine
import action.PatientDAO
import action.CityDAO
import model.Patient
import model.City

object ScalaJdbcConnectSelect extends App {


  
    var l = true
    while (l){

      println("\n\nDaftar Pasien RS\n-------------")
      
      PatientDAO.showData()

      println("-------------\n" +
        "1. Tambah Data\n" +
        "2. Ganti Data\n" +
        "3. Hapus Data\n" +
        "4. Data City\n" +
        "\n0. Exit\n\n")
      val pilih = readLine("pilih : ")
      if (pilih == "1"){
        val i_name = readLine("Masukan Nama : ")
        println("Daftar City\n-----------")
        CityDAO.showData()
        println("-----------")
        val i_city = readLine("Masukan id city : ").toInt
        val i_gender = readLine("Masukan gender (l/p) : ").toCharArray()(0)
        val i_date = readLine("Masukan date (yyyy-mm-dd) : ")
        val i_address = readLine("Masukan Alamat : ")

        val t_patient = Patient(0, i_city, i_name, i_gender, i_date, i_address)

        PatientDAO.addData(t_patient)

      } else if (pilih == "2"){

        val i_id = readLine("Masukan id : ").toInt
        println("Daftar City\n-----------")
        CityDAO.showData()
        println("-----------")
        val i_city = readLine("Masukan id city : ").toInt
        val i_name = readLine("Masukan Nama : ")
        val i_gender = readLine("Masukan gender (l/p) : ").toCharArray()(0)
        val i_date = readLine("Masukan date (yyyy-mm-dd) : ")
        val i_address = readLine("Masukan Alamat : ")

        val t_patient = Patient(i_id, i_city, i_name, i_gender, i_date, i_address)

        PatientDAO.updateData(t_patient)
        

      } else if (pilih == "3"){

        val id = readLine("Masukan Id : ").toInt
        PatientDAO.deleteData(id)

      } else if (pilih == "4") {

        var c = true
        while (c == true){

          println("Daftar City\n-------------")
          CityDAO.showData()
          println("-------------\n" +
            "1. Tambah Data\n" +
            "2. Ganti Data\n" +
            "3. Hapus Data\n" +
            "\n0. Exit\n\n")

            val pilih = readLine("pilih : ")

            if (pilih == "1"){
              val i_name = readLine("Masukan Nama city : ")
              val t_city = City(0, i_name)

              CityDAO.addData(t_city)

            } else if (pilih == "2"){

              val i_id = readLine("Masukan id city : ").toInt
              val i_name = readLine("Masukan Nama city : ")

              val t_city = City(i_id, i_name)

              CityDAO.updateData(t_city)

            }else if (pilih == "0"){
              c = false
            }
        }



      } else if (pilih=="0") {
        l = false
      }
    }



}
