package be.fabrice.onepage.business

import be.fabrice.onepage.business.bo.Language

case class LangueErrorCode(message:String)

object LangueErrorCode {
  val KEY_EXISTS = LangueErrorCode("Le code existe déjà")
}

class LanguageException(val code:LangueErrorCode) extends Exception(code.message)

object LanguageService {
	var languages = Map("Fr"->Language("Fr","Français"),"Eng"->Language("Eng","English"))
	
	def findAll = languages.values
	
	def find(key:String):Option[Language] = languages.get(key)
	
	def add(l:Language)= {
	  if(languages.keys.exists(_ == l.key)){
	    throw new LanguageException(LangueErrorCode.KEY_EXISTS)
	  } else{
	    languages+=(l.key -> l)
	  }
	}
}