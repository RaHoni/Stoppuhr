package Stoppuhr

import scala.swing._
import java.awt.{ComponentOrientation, Dimension}

class UI() extends MainFrame {
  title = "GUI Program #1"
  var time:Label = new Label("Das ist der Content")
  var Box: GridPanel = _
  var masterBox = new GridPanel(3,1){

    contents += time
    Box = new GridPanel(1,2){
      val s = Button("St") (main.handleS())
      val r = Button("Re") {main.handleR(); println(UI.this.contents(0).location)}


      contents += s
      contents += r
    }

    contents += Box
    contents += Button("Programm beenden") {sys.exit(0)}

  }
  contents = masterBox

}

