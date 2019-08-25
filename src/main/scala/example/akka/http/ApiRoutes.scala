package example.akka.http

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives
import spray.json._
import DefaultJsonProtocol._
import ConversionHelper._

class ApiRoutes extends SprayJsonSupport with Directives with LoggingHelper {
  val user = User(id = UUID.randomUUID(), firstName = "Test", lastName = "Akka")
  def createApiRoutes =
    
    path("user") {
      get {
        complete(user)
      } ~
      post {
        entity(as[User]) { user => 
          logDebug(user.toString)
          complete(UserCreatedStatusResult(Meta("OK", None, None), user))
        }
      }
    }
  
}
