package be.fabrice.onepage.controllers

import org.scalatra.ScalatraFilter
import org.scalatra.scalate.ScalateSupport
import org.scalatra.json.JacksonJsonSupport
import org.json4s.Formats
import org.json4s.DefaultFormats
import be.fabrice.onepage.business.bo.Language
import be.fabrice.onepage.business.LanguageService
import be.fabrice.onepage.business.LangueCodeErreur


case class Retour(val status:String,val messages:List[String])

class LanguageController  extends ScalatraFilter with ScalateSupport with JacksonJsonSupport{
  protected implicit val jsonFormats: Formats = DefaultFormats
  
  before() {
    contentType = formats("json")
  }
  
	get("/languages"){
		contentType="text/html"
		ssp("/languages")  
	}
	
	post("/languages"){
	  val l = parsedBody.extract[Language]
	  LanguageService.add(l) match {
	    case Some(LangueCodeErreur.KEY_EXISTS) => Retour("ko",List("Le code existe déjà"))
	    case None => Retour("ok",List("ok"))
	  }
	}
	
	get("/languages/list"){
	    LanguageService.findAll
	}
}