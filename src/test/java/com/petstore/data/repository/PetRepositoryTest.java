package com.petstore.data.repository;

import com.petstore.data.model.Gender;
import com.petstore.data.model.Pet;
import com.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class PetRepositoryTest {
    @Autowired
    PetRepository petRepository;

    @Autowired
    StoreRepository storeRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    public  void  whenPetIsSaved_ReturnPetId() {

        Pet pet = new Pet();

        pet.setName("Bull");

        pet.setAge(2);

        pet.setBreed("CrossBreeding");

        pet.setPetSex(Gender.MALE);

        log.info("Pet instance before saving --> {}", pet);
        petRepository.save(pet);

        Store store = new Store();

        store.setName("Pet seller");
        store.setLocation("Lagos");
        store.setContactNo("08023416");


        log.info("pet instance before saving --> {}", pet);
        pet.setStore(store);


        petRepository.save(pet);
        log.info("pet instance before saving --> {}", pet);


        assertThat(pet.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(pet.getId()).isNotNull();

    }

    @Test
    @Transactional
    @Rollback(value=false)
    public void whenIAddPetToAStoreICanFetchAllPetsInTheStore(){
        Store store = new Store();
        store.setLocation("Lagos");
        store.setName("Semicolon");
        store.setContactNo("0749736");

        Pet jane = new Pet();
        jane.setName("Jane");
        jane.setAge(5);
        jane.setColor("brown");
        jane.setPetSex(Gender.FEMALE);
        jane.setBreed("Cocacian Mastif");
        jane.setStore(store);

        petRepository.save(jane);


        Pet lilo = new Pet();

        lilo.setName("lilo");
        lilo.setAge(3);
        lilo.setColor("brown");
        lilo.setPetSex(Gender.MALE);
        lilo.setBreed("Rotweiler");
        lilo.setStore(store);

        petRepository.save(lilo);



        storeRepository.save(store);
        log.info("Pet instance before saving --> {}", store);

        store.addPets(lilo);
        store.addPets(jane);

        assertThat(lilo.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(jane.getId()).isNotNull();

    }


    @Test
    public void whenFindAllPetsIsCalledThenReturnAllPetsInTheStore(){
        List<Pet> savedPets = petRepository.findAll();

        log.info("fetched pet list from db --> {}", savedPets);

        assertThat(savedPets).isNotNull();
        assertThat(savedPets.size()).isEqualTo(6);
    }

    @Test
    public void updateExistingPetDetailsTest(){
        Pet pet = petRepository.findById(34).get();

        assertThat(pet).isNotNull();

        pet.setBreed("Elephant");
        petRepository.save(pet);
        assertThat(pet.getBreed()).isEqualTo("Elephant");


    }

    @Test
    public void whenIDeletePetFromDatabase_thenPetIsDeleted(){
        Boolean exists = petRepository.existsById(37);

        assertThat(exists).isTrue();

        petRepository.deleteById(37);
        assertThat(petRepository.existsById(37)).isFalse();

    }


        //log.info("Pet instance after saving --> {}", pet );

        //assertThat(pet.getId()).isNotNull();

    //public void whenStoreIsMappedToPet_thenForeignKeyIsPresent(){

}