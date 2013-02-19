package be.fabrice.onepage.controllers

import org.scalatra.test.scalatest.ScalatraSuite
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import be.fabrice.onepage.business.LanguageServiceComponent
import be.fabrice.onepage.dao.LanguageDaoComponent
import be.fabrice.onepage.dao.DataSourceComponent
import org.mockito.Mockito._
import be.fabrice.onepage.business.bo.Language
import org.scalatest.BeforeAndAfter
import org.mockito.Matchers._
import be.fabrice.onepage.business.LangueErrorCode
import be.fabrice.onepage.business.CodeRetour
import org.mockito.stubbing.Answer
import org.mockito.invocation.InvocationOnMock

trait TestLanguageServiceComponent extends LanguageServiceComponent with LanguageDaoComponent with DataSourceComponent with MockitoSugar {
  override val languageService = mock[LanguageService]
  override val languageDao = mock[LanguageDao]
  override val datasource = null
}

class LanguageControllerTestSuite  extends ScalatraSuite with FunSuite with BeforeAndAfter{
  val languageServiceComponent = new TestLanguageServiceComponent(){}
  val languageService = languageServiceComponent.languageService
  val languageDao = languageServiceComponent.languageDao

  addServlet(new LanguageController(languageServiceComponent),"/*")
  
  before{
    reset(languageService)
    when(languageService.findAll) thenReturn List(Language("test","test1"),Language("other","test2"))
    when(languageService.add(any[Language])) thenAnswer(new Answer[CodeRetour](){
      def answer(invocation:InvocationOnMock):CodeRetour = {
        invocation.getArguments()(0) match {
          case l:Language => if(l.key=="test") CodeRetour.KO(List(LangueErrorCode.KEY_EXISTS)) else CodeRetour.OK
          case _ => throw new RuntimeException
        }
      }
    })
  }
  
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
  
  test("post(/) must call LanguageServiceAdd"){
    post("/","{\"key\":\"uk\",\"name\":\"english\"}"){
      status must equal (200)
      verify(languageServiceComponent.languageService,times(1)).add(Language("uk","english"))
    }
  }
  
  test("post(/) must return ok"){
    post("/","{\"key\":\"uk\",\"name\":\"english\"}"){
      status must equal (200)
      body must include (""""status":"ok"""")
    }
  }
  
  test("post(/) with existing key must return ko"){
    post("/","{\"key\":\"test\",\"name\":\"must fail\"}"){
      status must equal (200)
      body must include (""""status":"ko"""")
    }
  }
}