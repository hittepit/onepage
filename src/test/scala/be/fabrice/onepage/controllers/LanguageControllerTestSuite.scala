package be.fabrice.onepage.controllers

import org.scalatra.test.scalatest.ScalatraSuite
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import be.fabrice.onepage.business.LanguageServiceComponent
import be.fabrice.onepage.dao.LanguageDaoComponent
import be.fabrice.onepage.dao.DataSourceComponent
import org.mockito.Mockito._
import be.fabrice.onepage.business.bo.Language

trait TestLanguageServiceComponent extends LanguageServiceComponent with LanguageDaoComponent with DataSourceComponent with MockitoSugar {
  override val languageService = mock[LanguageService]
  override val languageDao = mock[LanguageDao]
  override val datasource = null
  
  when(languageService.findAll) thenReturn List(Language("test","test1"),Language("other","test2"))
}

class LanguageControllerTestSuite  extends ScalatraSuite with FunSuite {
  val languageServiceComponent = new TestLanguageServiceComponent(){}

  addServlet(new LanguageController(languageServiceComponent),"/*")
  
  test("get(/list) must return json list of all languages"){
    get("/list"){
      status must equal (200)
      body must equal ("[{\"key\":\"test\",\"name\":\"test1\"},{\"key\":\"other\",\"name\":\"test2\"}]")
    }
  }
  
  test("get(/) must return base html page with angular.js"){
    get("/"){
      status must equal (200)
      body contains ("<script src=\"angular.min.js\">")
    }
  }
}