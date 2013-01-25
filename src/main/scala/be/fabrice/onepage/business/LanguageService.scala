package be.fabrice.onepage.business

import be.fabrice.onepage.business.bo.Language

object LanguageService {
	var languages = Map("Fr"->Language("Fr","Français"),"Eng"->Language("Eng","English"))
	
	def findAll = languages.values
	
	def add(l:Language):Boolean = {
	  if(languages.keys.exists(_ == l.key)){
	    false
	  } else {
	    languages+=(l.key -> l)
	    true
	  }
	}
}