package example.action

import example.model.Patient
import example.{Conn, Query}

class PatientDAO {
  val query = new Query()
  val conn = new Conn()

  def getData() : List[Patient] = {

    var patientList: List[Patient] = List()

    val squery = "SELECT * FROM patient"
    val rs = query.read(squery)

    while (rs.next) {
      val id = rs.getString("patient_id").toInt
      val city_id = rs.getString("city_id").toInt
      val name = rs.getString("patient_name")
      val gender = rs.getString("gender").charAt(0)
      val birth = rs.getString("date_birth")
      val address = rs.getString("address")

      val cityDAO = new CityDAO
      val city = cityDAO.getItem(city_id)

//      println(s"Pasien #$id\nNama : $name\nCity : $city_id\nGender : $gender\nLahir : $birth\nAlamat : $address\n")

      val patientBaru = Patient(Option(id),Option(city), Option(name), Option(gender), Option(birth), Option(address))::Nil
      patientList = patientList ::: patientBaru
    }

    query.close

    patientList

  }
   
  def addData( patient: Patient) = {

    val city = patient.city.get
    val nama = patient.patient_name.get
    val gender = patient.gender.get
    val date = patient.date_birth.get
    val address = patient.address.get

    val city_id = city.city_id.get

    val squery = s"INSERT INTO `patient`(`patient_name`,`city_id`, `gender`, `date_birth`, `address`) " +
              s"VALUES ('$nama','$city_id','$gender','$date','$address')"

    query.execute(squery)
  }

  def updateData( patient:Patient) = {

    val id = patient.patient_id.get
    val city = patient.city.get
    val nama = patient.patient_name.get
    val gender = patient.gender.get
    val date = patient.date_birth.get
    val address = patient.address.get

    val city_id = city.city_id.get

    val squery = s"UPDATE `patient` SET `patient_name` = '$nama',`city_id` = $city_id, `gender` = '$gender', `date_birth` = '$date', `address` = '$address' " +
      s"WHERE `patient`.`patient_id` = $id"
    println("QUERY : "+query)

    query.execute(squery)
  }

  def deleteData(id:Int) = {

    val squery = s"DELETE FROM `patient` WHERE `patient`.`patient_id` = $id"
    query.execute(squery)

  }

//  def getItem(id:Int) : Patient = {
//    val squery = s"SELECT * FROM `patient` WHERE `patient_id` = $id"
//
//    val row = query.read(squery)
//
//    row.next()
//    val name = row.getString("patient_name")
//    val city_id = row.getString("city_id").toInt
//    val gender = row.getString("gender").toCharArray[0]
//    val date = row.getString("date_birth")
//    val alamat = row.getString("address")
//
//    val cityDAO = new CityDAO
//    val city = cityDAO.getItem(city_id)
//
//    val patient = Patient(
//      Option(id),
//      Option(city),
//      Option(name),
//      Option(gender),
//      Option(date),
//      Option(alamat))
//
//    patient
//  }

}