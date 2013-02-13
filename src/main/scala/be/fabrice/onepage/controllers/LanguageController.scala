package be.fabrice.onepage.controllers

import org.scalatra.ScalatraFilter
import org.scalatra.scalate.ScalateSupport
import org.scalatra.json.JacksonJsonSupport
import org.json4s.Formats
import org.json4s.DefaultFormats
import org.slf4j.LoggerFactory
import be.fabrice.onepage.controllers.dto.LanguageDto
import be.fabrice.onepage.controllers.dto.LanguageValidator
import be.fabrice.onepage.application.ComponentRegistry
import be.fabrice.onepage.business.LanguageException


case class Retour(val status:String,val errors:Map[String,String])

class LanguageController extends ScalatraFilter with ScalateSupport with JacksonJsonSupport{
  protected implicit val jsonFormats: Formats = DefaultFormats
  
  val languageService = ComponentRegistry.languageService
  
  val logger = LoggerFactory.getLogger(classOf[LanguageController])
  
  before() {
    contentType = formats("json")
  }
  
	get("/languages"){
		contentType="text/html"
		ssp("/languages")  
	}
	
	post("/languages"){
	  val l = parsedBody.extract[LanguageDto]
	  var errors = LanguageValidator.validate(l,Map())
	  if(errors.isEmpty){
	    try{
		  languageService.add(l.transform)
	    }catch{
	      case e:LanguageException => errors = Map("language.key"->"La clé existe déjà")
	    }
	  }
	  
	  if(errors.isEmpty){
	    Retour("ok",Map())
	  } else {
	      logger.error("Il y a des erreurs")
	      Retour("ko",errors)
	  }
	}
	
	get("/languages/list"){
	    languageService.findAll
	}
}