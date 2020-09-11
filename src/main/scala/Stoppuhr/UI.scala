package Stoppuhr

import java.awt.Color

import scala.swing._
import javax.imageio.ImageIO

class UI() extends MainFrame {
  title = "Akzeptor"
  //iconImage = ImageIO.read(getClass.getResource("/icon.png"))
  //preferredSize = new Dimension(170,100)
  var input = new TextPane()
  val ausgabe = new Label()
  ausgabe.opaque = false
  var Box: Panel = _
  val s: Button = Button("Submit") (main.handleS())
  val r: Button = Button("R") {main.handleR()}


  Box = new FlowPanel(){
    contents += input
    contents += s
  }
  Box.opaque = false
  var masterBox: GridPanel = new GridPanel(3,1){
    contents += Box
    contents += ausgabe
    contents += r
  }

  masterBox.opaque = true
  masterBox.background = Color.GREEN
  contents = masterBox

  listenTo(this)
  reactions += {
    case event.UIElementResized(_) =>
      input.preferredSize = new Dimension(Box.size.width-s.size.width-15,s.size.height)
  }

}

