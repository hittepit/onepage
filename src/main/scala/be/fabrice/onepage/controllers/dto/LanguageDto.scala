package be.fabrice.onepage.controllers.dto

import be.fabrice.onepage.business.bo.Language

case class LanguageDto(val key:String,val name:String) {
  def transform = Language(key,name)
}

object LanguageValidator {
  def validate(language:LanguageDto, errors:Map[String,String]) = {
    var errorMap = errors
    if(language.key == null || language.key.isEmpty())
    	errorMap += ("language.key" -> "La clé ne peut être vide")
    
    if(language.name == null || language.name.isEmpty)
      errorMap += ("language.name" -> "Le nom ne peut être vide")
      
    errorMap
  }
}