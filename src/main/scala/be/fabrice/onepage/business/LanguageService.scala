package be.fabrice.onepage.business

import be.fabrice.onepage.business.bo.Language
import be.fabrice.onepage.dao.LanguageDaoComponent

case class LangueErrorCode(message:String)

object LangueErrorCode {
  val KEY_EXISTS = LangueErrorCode("Le code existe déjà")
}

class LanguageException(val code:LangueErrorCode) extends Exception(code.message)

trait LanguageServiceComponent {
  this:LanguageDaoComponent =>
    
  val languageService:LanguageService
  
  class LanguageService{
    def findAll = languageDao.findAll
    
   	def add(l:Language)= {
   	  languageDao.find(l.key) match {
   	    case None => languageDao.save(l)
   	    case Some(_) => throw new LanguageException(LangueErrorCode.KEY_EXISTS)
   	  }
	}

  }
}