package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import com.example.demo.pet.Pet;


public interface PetService {
    

    public List<Pet> getAllPetsByCategory(String category) ;

    public List<Pet> getPetsByCategoryAndColor(String category, String color) ;

    public Pet savePet(Pet pet);

    public boolean deletePet(Long id);
    
    public Optional<Pet> getById(Long id);
}
