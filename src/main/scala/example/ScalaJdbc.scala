package example

import scala.io.StdIn.readLine
import action.PatientDAO
import action.CityDAO
import model.Patient
import model.City

object ScalaJdbc extends App {

  def showPatient(patientList:List[Patient]): Unit ={
    for (item <- patientList){
      val id = item.patient_id.get
      val nama = item.patient_name.get
      val city = item.city.get.city_name.get
      val gender = item.gender.get
      val alamat = item.address.get

      println(s"\nPasien #$id\nNama : $nama\nKota : $city\nGender : $gender\nAlamat : $alamat")
    }
  }

  def showCity(cityList:List[City]): Unit ={

    for (item <- cityList){
      val id = item.city_id.get
      val nama = item.city_name.get

      println(s"\nKota #$id\nNama : $nama")
    }
  }

  val patientDAO = new PatientDAO
  val cityDAO = new CityDAO

  var l = true
  while (l){

    println("\n\nDaftar Pasien RS\n-------------")
    showPatient(patientDAO.getData())
    println("-------------\n" +
      "1. Tambah Data\n" +
      "2. Ganti Data\n" +
      "3. Hapus Data\n" +
      "4. Data City\n" +
      "\n0. Exit\n\n")
    val pilih = readLine("pilih : ")

    //tambah data
    if (pilih == "1"){
      val i_name = readLine("Masukan Nama : ")
      println("Daftar City\n-----------")
      showCity(cityDAO.getData())
      println("-----------")
      val i_city = readLine("Masukan id city : ").toInt
      val i_gender = readLine("Masukan gender (l/p) : ").toCharArray()(0)
      val i_date = readLine("Masukan date (yyyy-mm-dd) : ")
      val i_address = readLine("Masukan Alamat : ")

      val city = cityDAO.getItem(i_city)

      val t_patient = Patient(Option(0), Option(city), Option(i_name), Option(i_gender), Option(i_date), Option(i_address))

      patientDAO.addData(t_patient)

    } else if (pilih == "2"){

      val i_id = readLine("Masukan id : ").toInt
      println("Daftar City\n-----------")
      showCity(cityDAO.getData())
      println("-----------")
      val i_city = readLine("Masukan id city : ").toInt
      val i_name = readLine("Masukan Nama : ")
      val i_gender = readLine("Masukan gender (l/p) : ").toCharArray()(0)
      val i_date = readLine("Masukan date (yyyy-mm-dd) : ")
      val i_address = readLine("Masukan Alamat : ")

      val city = cityDAO.getItem(i_city)

      val t_patient = Patient(
        Option(i_id),
        Option(city),
        Option(i_name),
        Option(i_gender),
        Option(i_date),
        Option(i_address))

      patientDAO.updateData(t_patient)


    } else if (pilih == "3"){

      val id = readLine("Masukan Id : ").toInt
      patientDAO.deleteData(id)

    } else if (pilih == "4") {

      var c = true
      while (c == true){

        println("Daftar City\n-------------")
        showCity(cityDAO.getData())
        println("-------------\n" +
          "1. Tambah Data\n" +
          "2. Ganti Data\n" +
          "3. Hapus Data\n" +
          "\n0. Exit\n\n")

          val pilih = readLine("pilih : ")

          if (pilih == "1"){
            val i_name = readLine("Masukan Nama city : ")
            val t_city = City(Option(0), Option(i_name))

            cityDAO.addData(t_city)

          } else if (pilih == "2"){

            val i_id = readLine("Masukan id city : ").toInt
            val i_name = readLine("Masukan Nama city : ")

            val t_city = City(Option(i_id), Option(i_name))

            cityDAO.updateData(t_city)

          }else if (pilih == "0"){
            c = false
          }
      }



    } else if (pilih=="0") {
      l = false
    }
  }



}
