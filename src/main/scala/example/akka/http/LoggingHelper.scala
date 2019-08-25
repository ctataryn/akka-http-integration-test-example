package example.akka.http

import org.slf4j.LoggerFactory

/**
  * Created by craiger on 28/03/17.
  */
trait LoggingHelper {

  private def loggerImpl = LoggerFactory.getLogger(getClass())

  def logWarn(msg: => {def toString():String}) = if (loggerImpl.isWarnEnabled()) loggerImpl.warn(msg.toString())

  def logInfo(msg: => {def toString():String}) = if (loggerImpl.isInfoEnabled()) loggerImpl.info(msg.toString())

  def logDebug(msg: => {def toString():String}) = if (loggerImpl.isDebugEnabled()) loggerImpl.debug(msg.toString())

  def logTrace(msg: => {def toString():String}) = if (loggerImpl.isTraceEnabled()) loggerImpl.trace(msg.toString())

  def logError(t: Throwable, msg: => {def toString():String}) = if (loggerImpl.isTraceEnabled()) loggerImpl.error(msg.toString(), t)

  def logError(msg: => {def toString():String}) = if (loggerImpl.isTraceEnabled()) loggerImpl.error(msg.toString())

}
