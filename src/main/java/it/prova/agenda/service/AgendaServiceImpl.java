package it.prova.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.agenda.model.Agenda;
import it.prova.agenda.repository.agenda.AgendaRepository;

@Service
@Transactional(readOnly = true)
public class AgendaServiceImpl implements AgendaService{
	
	@Autowired
	AgendaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Agenda> listAllElements(boolean eager) {
		if (eager)
			return (List<Agenda>) repository.findAllAgendaEager();

		return (List<Agenda>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)

	public Agenda caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)

	public Agenda caricaSingoloElementoEager(Long id) {
		return repository.findSingoloElementoEager(id);
	}

	@Override
	@Transactional
	public Agenda aggiorna(Agenda agendaInstance) {
		return repository.save(agendaInstance);
	}

	@Override
	@Transactional
	public Agenda inserisciNuovo(Agenda agendaInstance) {
		return repository.save(agendaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Agenda agendaInstance) {
		repository.delete(agendaInstance);
	}

	@Override
	@Transactional(readOnly = true)

	public List<Agenda> cercaPerDescrizione(String descrizione) {
		return repository.findByDescrizione(descrizione);
	}

}
