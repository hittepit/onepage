package be.fabrice.onepage.business

import be.fabrice.onepage.business.bo.Language

case class LangueCodeErreur(message:String)

object LangueCodeErreur {
  val KEY_EXISTS = LangueCodeErreur("Le code existe déjà")
  val EMPTY_KEY = LangueCodeErreur("Le code est vide")
  val EMPTY_NAME = LangueCodeErreur("Le nom de la langue est vide")
}

object LanguageService {
	var languages = Map("Fr"->Language("Fr","Français"),"Eng"->Language("Eng","English"))
	
	def findAll = languages.values
	
	def add(l:Language):Option[LangueCodeErreur]= {
	  if(l.name.isEmpty()){
	    Some(LangueCodeErreur.EMPTY_KEY)
	  }
	  if(languages.keys.exists(_ == l.key)){
	    Some(LangueCodeErreur.KEY_EXISTS)
	  } else {
	    languages+=(l.key -> l)
	    None
	  }
	}
}