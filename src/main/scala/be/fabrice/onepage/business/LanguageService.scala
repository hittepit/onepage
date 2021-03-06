package be.fabrice.onepage.business

import be.fabrice.onepage.business.bo.Language
import be.fabrice.onepage.dao.LanguageDaoComponent

case class LangueErrorCode(message:String)

object LangueErrorCode {
  val KEY_EXISTS = LangueErrorCode("Le code existe déjà")
  val KEY_MUST_EXIST = LangueErrorCode("Le code à mettre à jour doit exister")
}

case class CodeRetour(val code:String, val errors:List[LangueErrorCode])

object CodeRetour {
  def OK = new CodeRetour("ok",List())
  def KO(errors:List[LangueErrorCode]) = new CodeRetour("ko",errors)
}

trait LanguageServiceComponent {
  this:LanguageDaoComponent =>
    
  val languageService:LanguageService
  
  class LanguageService{
    def findAll = languageDao.findAll
    
    def find(code:String) = languageDao.find(code)
    
   	def add(l:Language)= {
   	  languageDao.find(l.key) match {
   	    case None => languageDao.save(l)
   	    			CodeRetour.OK
   	    case Some(_) => CodeRetour.KO(List(LangueErrorCode.KEY_EXISTS))
   	  }
	}

    def save(l:Language) = {
   	  languageDao.find(l.key) match {
   	    case None => CodeRetour.KO(List(LangueErrorCode.KEY_MUST_EXIST))
   	  
   	    case Some(_) => languageDao.save(l)
   	    				CodeRetour.OK
   	  }
    }
  }
}