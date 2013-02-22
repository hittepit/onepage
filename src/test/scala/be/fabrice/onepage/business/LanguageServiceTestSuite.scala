package be.fabrice.onepage.business

import org.scalatest.FunSuite
import be.fabrice.onepage.dao.DataSourceComponent
import be.fabrice.onepage.dao.LanguageDaoComponent
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._
import org.mockito.Matchers._
import be.fabrice.onepage.business.bo.Language
import org.scalatest.BeforeAndAfter
import org.scalatest.matchers.MustMatchers

trait TestEnvironment extends LanguageServiceComponent with LanguageDaoComponent with DataSourceComponent with MockitoSugar{
  val languageService = new LanguageService //CUT
  val languageDao = mock[LanguageDao]
  val datasource = null
  
}

class LanguageServiceTestSuite extends FunSuite with BeforeAndAfter with MustMatchers with TestEnvironment{
	before{
	  reset(languageDao)
	  when(languageDao.find("new")) thenReturn None
	  when(languageDao.find("existing")) thenReturn Some(Language("existing","already there"))
	}
	
	test("add should succeed if code does not exit in DB"){
	  languageService.add(Language("new","new language"))
	}
	
	test("add should call save in dao in code does not exist"){
	  val l = Language("new","new language")
	  languageService.add(l)
	  verify(languageDao).save(l)
	}
	
	test("add must return ko if code already exists in DB"){
	  val s=  languageService.add(Language("existing","whatever"))
	  
	  s.code must equal ("ko")
	}
	
	test("save of an existing language must call save on dao and return OK"){
	  val l = Language("existing","whatever")
	  val s = languageService.save(l)
	  verify(languageDao).save(l)
	  
	  s.code must equal ("ok")
	}
	
	test("save of non existing language must return ko"){
	  val s = languageService.save(Language("new","a new hope"))
	  
	  s.code must equal ("ko")
	}
	
	test("save of non existing language must not call the dao"){
	  val s = languageService.save(Language("new","a new hope"))
	  verify(languageDao, times(0)).save(any())
	}
}