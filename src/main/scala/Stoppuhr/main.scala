package Stoppuhr

import java.awt.Color
import java.time.Instant
import java.util.concurrent.TimeUnit.{MILLISECONDS, SECONDS}

import Stoppuhr.zustaende._

import scala.Char
import scala.concurrent.duration.Duration
import scala.swing.Dimension



object main {
  var zustand: Stoppuhr.zustaende.zustaende = Start
  var ui: UI = _

  private val rNumber = """\d""".r
  private val rSing = """[-+]""".r

  def main(args: Array[String]): Unit = {
    ui = new UI()
    ui.visible = true
    ui.input.preferredSize = new Dimension(ui.Box.size.width-ui.s.size.width-15,ui.s.size.height)
    println("Fertig")


  }



  def handleS(): Unit = {
    if (!ui.input.text.isEmpty) {
      print(zustand)
      zustand match {
        case Start =>

          ui.input.text.head match {
            case rSing() => zustand = Sing
              ui.masterBox.background = Color.RED
            case rNumber() => zustand = Number
              ui.masterBox.background = Color.green
            case _ => zustand = Trash
              ui.masterBox.background = Color.red
          }
          case Sing =>
            ui.input.text.head match {
              case rNumber() => zustand = Number
                ui.masterBox.background = Color.green
              case _ => zustand = Trash
                ui.masterBox.background = Color.red
            }
          case Number =>
            ui.input.text.head match {
              case rNumber() =>
              case '.' => zustand = Point
                ui.masterBox.background = Color.RED
              case _ => zustand = Trash
                ui.masterBox.background = Color.RED
            }
          case Point =>
            ui.input.text.head match {
              case rNumber() => zustand = DecimalNumber
                ui.masterBox.background = Color.GREEN
              case _ => zustand = Trash
                ui.masterBox.background = Color.red
            }
          case DecimalNumber =>
            ui.input.text.head match {
              case rNumber() =>
              case _ => zustand = Trash
                ui.masterBox.background = Color.red
            }
          case Trash =>
        }

        ui.ausgabe.text += ui.input.text.head
        ui.input.text = ui.input.text.tail
    }
  }

  def handleR(): Unit = {
    zustand = Start
    ui.ausgabe.text = ""
    ui.masterBox.background = Color.green

  }
}