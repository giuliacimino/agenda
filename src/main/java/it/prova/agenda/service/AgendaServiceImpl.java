package it.prova.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.agenda.repository.agenda.AgendaRepository;
import it.prova.model.Agenda;

@Service
@Transactional(readOnly = true)
public class AgendaServiceImpl implements AgendaService{
	
	@Autowired
	AgendaRepository repository;

	@Override
	public List<Agenda> listAllElements(boolean eager) {
		if (eager)
			return (List<Agenda>) repository.findAllAgendaEager();

		return (List<Agenda>) repository.findAll();
	}

	@Override
	public Agenda caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Agenda caricaSingoloElementoEager(Long id) {
		return repository.findSingoloElementoEager(id);
	}

	@Override
	public Agenda aggiorna(Agenda agendaInstance) {
		return repository.save(agendaInstance);
	}

	@Override
	public Agenda inserisciNuovo(Agenda agendaInstance) {
		return repository.save(agendaInstance);
	}

	@Override
	public void rimuovi(Agenda agendaInstance) {
		repository.delete(agendaInstance);
	}

}
