package be.fabrice.onepage.business.bo

trait JsonConverter{
  def toJson:String
}

object ConvertToJson{
//  implicit def stringToJson(s:String) = new JsonConverter{
//    def toJson = "\""+s+"\""
//  }

//  implicit def propertyToJson(t:(String,Any)) = new JsonConverter{
//    def toJson = t._1.toJson+":"+(t._2 match{
//      case s:String => s.toJson
//      case a:Any => a.toJson
//    })
//  } 
  implicit def propertyToJson(t:(String,Any)) = new JsonConverter{
    def toJson = anyToJson(t._1)+":"+anyToJson(t._2)
  } 
  
  implicit def mapToJson(m:Map[Any,Any]) = new JsonConverter{
    def toJson = m.map{t:(Any,Any) => t.toJson}.mkString("{",",","}")
  }

  implicit def anyToJson(a:Any) = new JsonConverter{
    def toJson = a match {
      case s:String => "\""+s+"\""
      case _ => a.toString
    }
  }
}

class Person(val firstname:String,val lastname:String, var id:Option[Long]) {
	import ConvertToJson._
	
//	def toJson = "{firstname:"+firstname.toJson+",lastname:"+lastname.toJson+""+(id match {
//	  case None => "}"
//	  case Some(i) => ",id:"+i.toJson+"}"
//	})
//	def toJson = "{"+("firstname",firstname).toJson+","+("lastname",lastname).toJson+(id match {
//	  case None => "}"
//	  case Some(i) => ","+("id",i).toJson+"}"
//	})
	def toJson = Map("firstname"->firstname,"lastname"->lastname,"i"->id).toJson

}
