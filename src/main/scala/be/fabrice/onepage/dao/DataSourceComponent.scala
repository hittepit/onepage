package be.fabrice.onepage.dao

import javax.sql.DataSource

trait DataSourceComponent {
	val datasource:DataSource
	
}