package model

case class Patient(
  patient_id: Int , 
  city_id: Int,
  patient_name: String, 
  gender: Char, 
  date_birth: String, 
  address: String)