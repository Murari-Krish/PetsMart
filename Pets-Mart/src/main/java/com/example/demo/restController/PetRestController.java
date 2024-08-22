package com.example.demo.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pet.Pet;
import com.example.demo.service.PetService;

@RestController
@RequestMapping
public class PetRestController {

    @Autowired
    private PetService petServiceImpl;
    

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Pet>> getAllPetsByCategory(@PathVariable String category) {
        List<Pet> pets = petServiceImpl.getAllPetsByCategory(category);
        if (pets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping("/category/{category}/color/{color}")
    public ResponseEntity<List<Pet>> getPetsByCategoryAndColor(@PathVariable String category, @PathVariable String color) {
        List<Pet> pets = petServiceImpl.getPetsByCategoryAndColor(category, color);
        if (pets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }


    @PostMapping("/savepet")
    public ResponseEntity<Pet> savePet(@RequestBody Pet pet) {
        Pet savedPet = petServiceImpl.savePet(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }
  
       
     @GetMapping("/pet/{id}")
     public ResponseEntity<?> getPetById(@PathVariable Long id) {
         try {
             Optional<Pet> pet = petServiceImpl.getById(id);
             if (pet != null) {
                 return ResponseEntity.ok(pet);
             } else {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Pet not found");
             }
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Could not retrieve the pet");
         }
     }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        boolean isDeleted = petServiceImpl.deletePet(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}