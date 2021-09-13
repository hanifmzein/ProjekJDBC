package example.model

case class Patient(
                    patient_id: Option[Int],
                    city: Option[City],
                    patient_name: Option[String],
                    gender: Option[Char],
                    date_birth: Option[String],
                    address: Option[String])
