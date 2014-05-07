import org.junit.After
import org.junit.Before
import org.junit.Test
import org.monasso.domain.Personne
import org.monasso.domain.Voiture

import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery
import com.orientechnologies.orient.object.db.OObjectDatabaseTx

import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery
import com.orientechnologies.orient.object.db.OObjectDatabaseTx

class SimpleTest {

	OObjectDatabaseTx db

	@Before
	void open() {
		db = new OObjectDatabaseTx("memory:demo")
		db.create()
		db.getEntityManager().registerEntityClasses("org.monasso.domain")
	}

	@Test
	void testPersistPersonne() {
		Personne personne = db.newInstance(Personne.class)
		personne.nom = 'Toto'
		personne.prenom = 'Titi'

		db.save(personne)
		db.commit()
		
		assert(db.countClass("Personne") == 1)
		
		def result = db.query(new OSQLSynchQuery<ODocument>("select * from Personne"))
		assert(result.size() == 1)
		def r = result.get(0) as Personne
		assert r != null
		assert r.nom == 'Toto'
		assert r.prenom == 'Titi'
	}
	
	@After
	void close() {
		db.drop()
		db.close()
	}

}

