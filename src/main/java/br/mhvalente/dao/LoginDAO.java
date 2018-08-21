package br.mhvalente.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.mhvalente.domain.Usuario;

public class LoginDAO {
	private EntityManager em;
	private Session session;
	
	public LoginDAO(){
		em = Conexao.getConnection();
		session = em.unwrap(Session.class);
	}
	
	public Usuario consultar(String login, String senha) {
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.like("login", login, MatchMode.EXACT));
		criteria.add(Restrictions.like("senha", senha, MatchMode.EXACT));
		return (Usuario) criteria.uniqueResult();
	}
	
	public List<Usuario> getUsers() {
		return session.createCriteria(Usuario.class).list(); 
	}
	
}
