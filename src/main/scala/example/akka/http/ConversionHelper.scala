package example.akka.http

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol._
import spray.json._

import scala.util.Try

object ConversionHelper extends DefaultJsonProtocol with SprayJsonSupport  {

  implicit object UUIDJsonFormat extends JsonFormat[UUID] {
    def write(x: UUID) = {
      require(x ne null)
      JsString(x.toString)
    }
    def read(value: JsValue) = {
      def stringToUUID(s: String): UUID = Try(UUID.fromString(s))
        .recover { case t => deserializationError("Expected a valid UUID, but got " + s) }
        .get
      value match {
        case JsString(x) => stringToUUID(x)
        case x           => deserializationError("Expected UUID as JsString, but got " + x)
      }
    }
  }
  
  implicit val metaJsonFormat = jsonFormat3(Meta)

  implicit val emptyStatusResultJsonFormat = jsonFormat1(EmptyStatusResult)

  implicit val userFormat = jsonFormat3(User)
  implicit val userCreatedStatusResultJsonFormat = jsonFormat2(UserCreatedStatusResult)
  
  
}
