package Stoppuhr

import java.time.Instant
import java.util.concurrent.TimeUnit.{MILLISECONDS, SECONDS}

import Stoppuhr.zustaende._

import scala.concurrent.duration.Duration



object main {
  var zustand: Stoppuhr.zustaende.Value = Start
  var showRound: Boolean = false
  var start: Long = 0
  var alteDauer: Duration = Duration(0,SECONDS)
  var rundenDauer: Duration = Duration(0,SECONDS)
  var ui: UI = _

  def main(args: Array[String]): Unit = {
    ui = new UI()
    ui.visible = true
    println("Fertig")
    while (true) {
    showTime()}

  }

  def showTime(): Unit = {
    if(showRound) {
      ui.time.text = formatDuration(rundenDauer)
    } else if(zustand==Running) {
      ui.time.text = formatDuration(duration); return }
    else if(zustand==Pause) {ui.time.text = formatDuration(alteDauer); return }
    else ui.time.text = formatDuration(Duration(0,SECONDS))
    ui.time.text
  }

  def formatDuration(duration: Duration) = {String.format("%02d:%02d:%02d",duration.toMinutes.toInt,(duration.toSeconds%60).toInt,duration.toMillis%1000)}

  def millisecondsNow: Long = Instant.now().toEpochMilli

  def duration: Duration = alteDauer + Duration(millisecondsNow-start,MILLISECONDS)

  def handleS(): Unit = {
    zustand match  {
      case Start => {
        start =  millisecondsNow
        zustand = Running
      }
      case Running => {
        alteDauer = duration
        zustand = Pause
      }
      case Pause => {

        start = millisecondsNow
        zustand = Running
      }
    }
    println(zustand)
  }

  def handleR(): Unit = {
    if(!showRound){
      zustand match {
        case Pause => {
          alteDauer = Duration(0,SECONDS)
          zustand = Start
        }
        case Start =>
        case Running => {
          rundenDauer = duration
          showRound = true
        }
      }
    } else {
      zustand match {
        case Pause => showRound = false
        case Start =>
        case Running => showRound = false
      }
    }
    println(zustand)
  }
}