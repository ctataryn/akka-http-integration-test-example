package example.akka.http

case class Meta(status: String, code: Option[String], details: Option[scala.collection.immutable.Map[String,String]])

trait StatusResult {
  def meta: Meta
}


case class EmptyStatusResult(meta: Meta) extends StatusResult

case class UserCreatedStatusResult(meta: Meta, user: User)
