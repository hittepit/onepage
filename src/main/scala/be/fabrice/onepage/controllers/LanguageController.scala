package be.fabrice.onepage.controllers

import org.scalatra.ScalatraFilter
import org.scalatra.scalate.ScalateSupport
import org.scalatra.json.JacksonJsonSupport
import org.json4s.Formats
import org.json4s.DefaultFormats
import be.fabrice.onepage.business.bo.Language
import be.fabrice.onepage.business.LanguageService
import be.fabrice.onepage.business.LangueCodeErreur
import org.slf4j.LoggerFactory


case class Retour(val status:String,val messages:List[String],val errors:Map[String,Boolean])

class LanguageController extends ScalatraFilter with ScalateSupport with JacksonJsonSupport{
  protected implicit val jsonFormats: Formats = DefaultFormats
  
  val logger = LoggerFactory.getLogger(classOf[LanguageController])
  
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
	    case Nil => logger.info("Input correct")
	      			Retour("ok",List("ok"),Map())
	    case l:List[LangueCodeErreur] => {
	      def addCodes(l:List[LangueCodeErreur],r:Retour):Retour = l match {
	        case x::xs => addCodes(xs,Retour("ko",x.message::r.messages,r.errors+ (x.key->true)))
	        case Nil => r
	      }
	      
	      logger.error("Il y a des erreurs")
	      addCodes(l,Retour("ko",Nil,Map()))
	    } 
	  }
	}
	
	get("/languages/list"){
	    LanguageService.findAll
	}
}