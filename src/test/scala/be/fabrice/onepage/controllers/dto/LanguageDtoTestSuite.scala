package be.fabrice.onepage.controllers.dto

import org.scalatest.matchers.MustMatchers
import org.scalatest.FunSuite

class LanguageDtoTestSuite extends FunSuite with MustMatchers{
	test("LanguageDto must produce a identical Language"){
	  val ldto = new LanguageDto("test","This is a test")
	  val l = ldto.transform
	  l.key must equal ("test")
	  l.name must equal ("This is a test")
	}
}