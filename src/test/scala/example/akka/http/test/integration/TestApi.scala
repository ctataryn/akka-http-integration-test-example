package example.akka.http.test.integration

import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import example.akka.http.{EmptyStatusResult, User}
import example.akka.http.ConversionHelper._
import org.specs2.mutable.Specification

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import akka.http.scaladsl.client.RequestBuilding._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json._
import DefaultJsonProtocol._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer

class TestApi extends Specification {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  
  def waitOnResponse(request: HttpRequest): HttpResponse = {
    Await.result(Http().singleRequest(request), Duration("20s"))
  }
  
  sequential
  
  val endpoint = "http://localhost:8080/user"
  val knownUuid = UUID.fromString("05ea4e86-c742-11e9-aa8c-2a2ae2dbcce4")
  var dto:User = null
  step {
    dto = Await.result(Unmarshal(waitOnResponse(Get(endpoint))).to[User], Duration("20s"))
  }

  "calling GET /user" should {
    "return {meta:{status:OK}, user: User }" in {
      dto.copy(id = knownUuid) must beEqualTo(User(knownUuid, "Test", "Akka"))
    }
  }
  
}
