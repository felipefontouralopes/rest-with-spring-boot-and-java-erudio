package br.com.erudio.services;

import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    //private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        //Logger.info("Finding one Person!");

        //Mock para teste, substituindo necessidade de buscar dados no banco
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Leandro");
        person.setLastName("Costa");
        person.setAddress("Uberlândia - Minas Gerais - Brasil");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll(){
        //Logger.info("Finding all People!");
        List<Person> persons = new ArrayList<Person>();

        //Mock para teste
        for (int i=0; i<8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person){
        //Logger.info("Creating one Person");
        return person;
    }

    public Person update(Person person){
        //Logger.info("Updating one Person");
        return person;
    }

    public void delete(String id){
        //Logger.info("Deleting one Person");
    }

    //Metodo para criar mock
    private Person mockPerson(int i){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Brasil");
        person.setGender("Male");
        return person;
    }

}
