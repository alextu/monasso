import com.orientechnologies.orient.object.db.OObjectDatabaseTx
import org.junit.Test
import org.junit.Before
import org.junit.After
import org.monasso.domain.Personne

class SimpleTest {

	OObjectDatabaseTx db

	@Before
	void open() {
		db = new OObjectDatabaseTx("memory:demo")
		if(db.exists()) {
			db.open("admin", "admin")
			db.drop()
		}
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
	}

	@After
	void close() {
		if (db.exists()) {
			db.drop()
		}
	}

}

