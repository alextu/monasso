import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.monasso.domain.Personne;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class NotSoSimpleTest {

	OObjectDatabaseTx db;
	
	public NotSoSimpleTest() {
		// TODO Auto-generated constructor stub
	}

	@Before
	public void open() {
		db = new OObjectDatabaseTx("memory:demo");
		db.create();
		db.getEntityManager().registerEntityClasses("org.monasso.domain");
	}

	@Test
	public void testPersistPersonne() {
		Personne personne = db.newInstance(Personne.class);
		personne.setNom("Toto");
		personne.setPrenom("Titi");

		db.save(personne);
		db.commit();
		
		assert(db.countClass("Personne") == 1);
		
		List<?> result = db.query(new OSQLSynchQuery<ODocument>("select * from Personne"));
		assert(result.size() == 1);
		Personne r = (Personne) result.get(0);
		assert(r != null);
		System.out.println(r.getNom());
		System.out.println(r.getPrenom());
	}

	@After
	public void close() {
		db.close();
	}

}

