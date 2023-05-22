package it.prova.agenda.repository.agenda;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.model.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, Long>{
	
	@Query("select a from Agenda a join fetch a.utente")
	List<Agenda> findAllAgendaEager();
	
	
	@Query("select a from Agenda a join fetch a.utente where a.id=?")
	Agenda findSingoloElementoEager(Long id);
	

}
