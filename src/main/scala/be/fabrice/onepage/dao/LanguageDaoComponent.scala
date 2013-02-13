package be.fabrice.onepage.dao

import be.fabrice.onepage.business.LanguageException
import be.fabrice.onepage.business.bo.Language

trait LanguageDaoComponent {
	this:DataSourceComponent =>
	  
	val languageDao:LanguageDao
	
	class LanguageDao {
	    var languages = Map("Fr"->Language("Fr","Français"),"Eng"->Language("Eng","English"))
	
	    def findAll = languages.values
	
	    def find(key:String):Option[Language] = languages.get(key)
	
	    def save(language:Language) = languages+=(language.key -> language)
	}
}
