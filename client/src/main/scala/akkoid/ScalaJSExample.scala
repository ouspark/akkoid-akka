package akkoid

import org.scalajs.dom
import org.scalajs.dom.Element
import org.scalajs.dom.raw.Event
import scalatags.JsDom.all._

import scala.scalajs.js.annotation.JSExport

@JSExport
object ScalaJSExample {

  val document = dom.document

  def toggleClass(elem: Element, className: String) = {
    if(elem.classList != null && elem.classList.contains(className))
      elem.classList.remove(className)
    else elem.classList.add(className)
  }

  def toggleAll = { e: Event =>
    val className = "active"
    e.preventDefault()
    toggleClass(document.getElementById("layout"), className)
    toggleClass(document.getElementById("menu"), className)
    toggleClass(document.getElementById("menuLink"), className)
  }

  def toggleContent = { e: Event =>
    if(document.getElementById("menu").classList.contains("active"))
      toggleAll(e)
  }

  val layout =
    div(id:="layout")(
      a(href:="#menu", id:="menuLink", cls:="menu-link", onclick:= { e: Event => toggleAll(e) })(
        span()
      ),
      div(id:="menu")(
        div(cls:="pure-menu")(
          a(cls:="pure-menu-heading", href:="#")("Company"),
          ul(cls:="pure-menu-list")(
            li(cls:="pure-menu-item")(
              a(cls:="pure-menu-link menu-item-divided pure-menu-selected", href:="#")("Home")
            ),
            li(cls:="pure-menu-item")(
              a(cls:="pure-menu-link", href:="#")("About")
            ),
            li(cls:="pure-menu-item")(
              a(cls:="pure-menu-link", href:="#")("Service")
            ),
            li(cls:="pure-menu-item")(
              a(cls:="pure-menu-link", href:="#")("Contact")
            )
          )
        )
      ),
      div(id:="main", onclick:={e: Event => toggleContent(e) })(
        div(cls:="header")(
          h1("Page Title"),
          h2("A subtitle for your page goes here")
        ),
        div(cls:="content")(
          h2(cls:="content-subhead")("How to use this layout"),
          p("this is ppppppppppppppppp"),
          h2(cls:="content-subhead")("Now let's speak some nonsense"),
          p("asdfafadfadfsadsfafdafafdadfafd"),
          div(cls:="pure-g")(
            "hahahah"
          )

        )
      )
    ).render

  @JSExport
  def main(args: Array[String]): Unit = {
      dom.document.body.insertBefore(layout, dom.document.body.firstChild)//.appendChild(layout)

//    for(s <- AjaxClient[ApiTest].messageTest("first", "second").call()) {
//      dom.document.getElementById("root").textContent = s.name
//    }

  }
}
