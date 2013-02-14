package be.fabrice.onepage.application

import be.fabrice.onepage.business.LanguageServiceComponent
import be.fabrice.onepage.dao.LanguageDaoComponent
import be.fabrice.onepage.dao.DataSourceComponent

trait LanguageComponent extends LanguageServiceComponent with LanguageDaoComponent with DataSourceComponent{
	val datasource = null
	val languageService = new LanguageService
	val languageDao = new LanguageDao
}

trait ComponentRegistry extends LanguageComponent