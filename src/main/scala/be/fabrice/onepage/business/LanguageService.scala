package be.fabrice.onepage.business

import be.fabrice.onepage.business.bo.Language

case class LangueCodeErreur(message:String,key:String)

object LangueCodeErreur {
  val KEY_EXISTS = LangueCodeErreur("Le code existe déjà","codeError")
  val EMPTY_KEY = LangueCodeErreur("Le code est vide","codeError")
  val EMPTY_NAME = LangueCodeErreur("Le nom de la langue est vide","nameError")
}

object LanguageService {
	var languages = Map("Fr"->Language("Fr","Français"),"Eng"->Language("Eng","English"))
	
	def findAll = languages.values
	
	def add(l:Language):List[LangueCodeErreur]= {
	  var errors = List[LangueCodeErreur]()
	  if(l.key.isEmpty()){
	    errors::=LangueCodeErreur.EMPTY_KEY
	  }
	  if(l.name.isEmpty){
	    errors::=LangueCodeErreur.EMPTY_NAME
	  } 
	  
	  if(languages.keys.exists(_ == l.key)){
	    errors::=LangueCodeErreur.KEY_EXISTS
	  } else if(errors.isEmpty){
	    languages+=(l.key -> l)
	  }
	  
	  errors
	}
}