import org.scalatra.servlet.RichRequest
import javax.servlet.http.HttpServletRequest
import org.scalatra.LifeCycle
import javax.servlet.ServletContext
import be.fabrice.onepage.controllers.LanguageController
import be.fabrice.onepage.application.ComponentRegistry

class ScalatraBootstrap extends LifeCycle {
	override def init(context:ServletContext){
	  val registry = new ComponentRegistry(){}
	  context mount (new LanguageController(registry), "/languages/*")
	}
}