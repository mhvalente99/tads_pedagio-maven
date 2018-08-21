package br.mhvalente.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.mhvalente.domain.Pedagio;

public class PedagioDAO {
	private EntityManager em;
	private Session session;
	
	public PedagioDAO() {
		em = Conexao.getConnection();
		session = em.unwrap(Session.class);
	}
	
	public Pedagio cadastrar(Pedagio pedagio) throws Exception {
		EntityTransaction trasaction = em.getTransaction();
		
		trasaction.begin();
		
		try {
			pedagio = em.merge(pedagio);
			trasaction.commit();
			
			return pedagio;
		} catch (Exception e) {
			trasaction.rollback();
			throw e;
		}
	}

	public List<Pedagio> buscarPedagio() throws SQLException {
		return session.createCriteria(Pedagio.class).list();
	}
	
	public Pedagio buscarPedagio(int codigo) {
		Criteria criteria = session.createCriteria(Pedagio.class);
		criteria.add(Restrictions.eq("codigo", codigo));
		return (Pedagio) criteria.uniqueResult();
	}
	
	public void excluir(Pedagio pedagio) throws Exception {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		try {
			em.remove(pedagio);
			transaction.commit();
			
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		}
		
	}
}
