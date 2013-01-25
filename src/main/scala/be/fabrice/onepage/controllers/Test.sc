package be.fabrice.onepage.controllers

import be.fabrice.onepage.business.bo.Person


object Test {
	val p = new Person("Fabrice","Claes",Some(1))
                                                  //> p  : be.fabrice.onepage.business.bo.Person = be.fabrice.onepage.business.bo.
                                                  //| Person@509df6f1

 
	p.toJson                                  //> res0: String = Map(firstname -> Fabrice, lastname -> Claes, i -> Some(1))

	Json.json(List(true))                     //> res1: String = [true]
}

object Json {
	def json(a:Any) = a.toString
	def json(n:Number)= n.toString
	def json(s:String)= "\""+s+"\""
	
	def json(b:Boolean) = if(b) "true" else "false"
	
	def json(l:List[Any]):String = l.map(json(_)).mkString("[",",","]")
}