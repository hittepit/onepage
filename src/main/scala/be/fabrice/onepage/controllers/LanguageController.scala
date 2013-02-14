package be.fabrice.onepage.controllers

import org.json4s.DefaultFormats
import org.json4s.Formats
import org.json4s.jvalue2extractable
import org.scalatra.ScalatraServlet
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.scalate.ScalateSupport
import org.slf4j.LoggerFactory

import be.fabrice.onepage.application.ComponentRegistry
import be.fabrice.onepage.business.LanguageException
import be.fabrice.onepage.controllers.dto.LanguageDto
import be.fabrice.onepage.controllers.dto.LanguageValidator


case class Retour(val status:String,val errors:Map[String,String])

class LanguageController extends ScalatraServlet with ScalateSupport with JacksonJsonSupport with ComponentRegistry{
  protected implicit val jsonFormats: Formats = DefaultFormats
  
  val logger = LoggerFactory.getLogger(classOf[LanguageController])
  
  before() {
    contentType = formats("json")
  }
  
	get("/"){
		contentType="text/html"
		ssp("/languages")  
	}
	
	post("/"){
	  val l = parsedBody.extract[LanguageDto]
	  var errors = LanguageValidator.validate(l,Map())
	  if(errors.isEmpty){
	    try{
		  languageService.add(l.transform)
	    }catch{
	      case e:LanguageException => errors = Map("language.key"->e.code.message)
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