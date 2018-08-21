package br.mhvalente.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Conexao {
	public static EntityManager getConnection() {
		return Persistence.createEntityManagerFactory("AulaPED").createEntityManager();
	}
}
