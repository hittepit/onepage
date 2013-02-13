package be.fabrice.onepage.controllers.dto

import org.scalatest.FunSuite
import org.scalatest.matchers.MustMatchers

class LanguageValidatorTestSuite extends FunSuite with MustMatchers {
	test("LanguageDto is not valid if no key defined"){
	  val l = new LanguageDto(null,"toto")
	  val errors = LanguageValidator.validate(l,Map())
	  errors.size must equal(1)
	  errors must contain key ("language.key")
	  errors must contain value ("La clé ne peut être vide")
	}
	
	test("LanguageDto is not valid if key is an empty String"){
	  val l = new LanguageDto("","toto")
	  val errors = LanguageValidator.validate(l,Map())
	  errors.size must equal(1)
	  errors must contain key ("language.key")
	  errors must contain value ("La clé ne peut être vide")
	}
	
	test("LanguageValidator must add new errors to existing ones"){
	  val err = Map("test"->"this is a test")
	  val l = new LanguageDto(null,"toto")
	  val errors = LanguageValidator.validate(l,err)
	  errors.size must equal(2)
	  errors must contain key ("test")
	  errors must contain value ("this is a test")
	}
	
	test("LanguageDto is not valid if no name given"){
	  val l = new LanguageDto("toto",null)
	  val errors = LanguageValidator.validate(l,Map())
	  errors.size must equal(1)
	  errors must contain key ("language.name")
	  errors must contain value ("Le nom ne peut être vide")
	}
	
	test("LanguageDto is not valid if name is an empty String"){
	  val l = new LanguageDto("toto","")
	  val errors = LanguageValidator.validate(l,Map())
	  errors.size must equal(1)
	  errors must contain key ("language.name")
	  errors must contain value ("Le nom ne peut être vide")
	}
}