package example.akka.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directive0
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import org.slf4j.bridge.SLF4JBridgeHandler

object Main {
  
  def getContextPathDirective(contextPath: String): Directive0 = {
    contextPath.stripPrefix("/") match {
      case "" =>  pathPrefixTest(Neutral)
      case p  => pathPrefix(p)
    }
  }
  
  def main(args: Array[String]) {
    implicit val system: ActorSystem = ActorSystem("example")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher
    
    //install SLF4J in favour of any dependency that logs using java.util.logging
    SLF4JBridgeHandler.removeHandlersForRootLogger()
    SLF4JBridgeHandler.install()
    
    setLogLevel("DEBUG")

    Http().bindAndHandle(new ApiRoutes().createApiRoutes, interface = "localhost", port = 8080)
    //IO(Http) ! Http.Bind(handler, interface = settings.bindInterface, port = settings.bindPort)
  }
  
  def setLogLevel(logLevel: String): Unit = {
    val root = org.slf4j.LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME).asInstanceOf[ch.qos.logback.classic.Logger]
    root.setLevel(ch.qos.logback.classic.Level.valueOf(logLevel))
  }
}
