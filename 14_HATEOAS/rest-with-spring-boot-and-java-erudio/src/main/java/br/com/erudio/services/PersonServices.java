package br.com.erudio.services;

import br.com.erudio.controllers.PersonController;
import br.com.erudio.data.dto.PersonDTO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.mapper.ObjectMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonDTO> findAll(){
        logger.info("Encontrando todas as pessoas!");

        //return repository.findAll();
        var persons = ObjectMapper.parseListObjects(repository.findAll(), PersonDTO.class);
        persons.forEach(this::AddHateoasLinks);
        return persons;
    }

    public PersonDTO findById(Long id){
        logger.info("Encontrando uma pessoa");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto = ObjectMapper.parseObject(entity, PersonDTO.class);
        AddHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Criando uma pessoa!");

        //return repository.save(person);
        var entity = ObjectMapper.parseObject(person, Person.class);
        var dto = ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
        AddHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Atualizando uma pessoa!");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = ObjectMapper.parseObject(repository.save(entity), PersonDTO.class);
        AddHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Excluindo uma pessoa!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }

    private void AddHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}

