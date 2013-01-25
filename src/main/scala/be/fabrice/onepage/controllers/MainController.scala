package be.fabrice.onepage.controllers
import org.scalatra.ScalatraFilter
import org.scalatra.scalate.ScalateSupport
import org.scalatra.ScalatraServlet

class MainController extends ScalatraFilter {
	get("/"){
		"Hello world"
	}

}