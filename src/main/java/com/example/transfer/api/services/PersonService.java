package com.example.transfer.api.services;

import com.example.transfer.api.exceptions.NotFoundPersonException;
import com.example.transfer.api.models.Person;
import com.example.transfer.api.models.Transfer;
import com.example.transfer.api.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundPersonException("Pessoa não encontrada"));
    }

    @Transactional
    public void updatePerson(Person person, Transfer transfer) {
        person.getTransfers().add(transfer);
        personRepository.save(person);
    }
}
