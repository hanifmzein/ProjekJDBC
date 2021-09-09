package model

case class Patient(id: Option[Int], email: String, 
  firstName: Option[String], lastName: Option[String])