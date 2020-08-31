package Stoppuhr

import scala.swing._
import javax.imageio.ImageIO

class UI() extends MainFrame {
  title = "Stoppuhr"
  iconImage = ImageIO.read(getClass.getResource("/icon.png"))
  preferredSize = new Dimension(170,100)
  var time:Label = new Label("")
  var Box: GridPanel = _
  var masterBox = new GridPanel(3,1){

    contents += time
    Box = new GridPanel(1,2){
      val s = Button("S") (main.handleS())
      val r = Button("R") {main.handleR()}


      contents += s
      contents += r
    }

    contents += Box
    contents += Button("Programm beenden") {sys.exit(0)}

  }
  contents = masterBox

}

