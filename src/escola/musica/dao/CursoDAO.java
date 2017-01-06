package escola.musica.dao;

import java.util.List;

import javax.persistence.EntityManager;

import escola.musica.modelo.Curso;

public class CursoDAO {

	public void salvar(Curso curso) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.merge(curso);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Curso> listarTodos() {
		EntityManager entityManager = JPAUtil.getEntityManager();
		return entityManager.createQuery("from Curso").getResultList();
	}

	public void excluir(Curso curso) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		entityManager.getTransaction().begin();
		
		// Sem esta linha o curso não pode ser excluído.
		// Esta linha liga o meu curso com o curso do banco.
		// Por causa do estado do Objecto, detached, transient, etc.
		// Não sei se este é melhor forma de colocar o objeto no estado para excluir.
		curso = entityManager.merge(curso);
		
		entityManager.remove(curso);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
}