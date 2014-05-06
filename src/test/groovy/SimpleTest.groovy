import com.orientechnologies.orient.object.db.OObjectDatabaseTx
import org.junit.Test
import org.junit.Before
import org.junit.After
import org.monasso.domain.Personne

class SimpleTest {

	OObjectDatabaseTx db

	@Before
	void open() {
		db = new OObjectDatabaseTx("remote:localhost/demo")
		db.getEntityManager().registerEntityClasses("org.monasso.domain")
		db.open("admin", "admin")
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
		db.close()
	}

}

