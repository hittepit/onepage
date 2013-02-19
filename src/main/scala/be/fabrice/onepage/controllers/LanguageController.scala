package be.fabrice.onepage.controllers

import org.json4s.DefaultFormats
import org.json4s.Formats
import org.json4s.jvalue2extractable
import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.scalate.ScalateSupport
import org.slf4j.LoggerFactory
import be.fabrice.onepage.application.ComponentRegistry
import be.fabrice.onepage.controllers.dto.LanguageDto
import be.fabrice.onepage.controllers.dto.LanguageValidator
import be.fabrice.onepage.business.LanguageServiceComponent
import be.fabrice.onepage.business.CodeRetour


case class Retour(val status:String,val errors:Map[String,String])

class LanguageController(val languageServiceComponent:LanguageServiceComponent) extends ScalatraServlet with ScalateSupport with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats
  
  val languageService = languageServiceComponent.languageService
  val logger = LoggerFactory.getLogger(classOf[LanguageController])
  
  before() {
    contentType = formats("json")
  }
  
	get("/"){
		contentType="text/html"
		ssp("/languages")  
	}
	
	post("/"){
	  val l = parse(request.body).extract[LanguageDto]
//	  val l = parsedBody.extract[LanguageDto]
	  var errors = LanguageValidator.validate(l,Map())
	  if(errors.isEmpty){
	     languageService.add(l.transform) match {
	       case CodeRetour("ok",_) =>
	       case CodeRetour("ko",errs) =>errors = Map("language.key"->errs(0).message)
	     }
	  }
	  
	  if(errors.isEmpty){
	    Retour("ok",Map())
	  } else {
	      logger.error("Il y a des erreurs")
	      Retour("ko",errors)
	  }
	}
	
	get("/list"){
	    languageService.findAll
	}
}