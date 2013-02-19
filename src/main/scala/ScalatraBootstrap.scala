import org.scalatra.servlet.RichRequest
import javax.servlet.http.HttpServletRequest
import org.scalatra.LifeCycle
import javax.servlet.ServletContext
import be.fabrice.onepage.controllers.LanguageController

class ScalatraBootstrap extends LifeCycle {
	override def init(context:ServletContext){
	  context mount (new LanguageController, "/languages/*")
	}
}