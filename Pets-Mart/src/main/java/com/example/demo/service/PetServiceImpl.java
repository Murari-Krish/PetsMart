package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pet.Pet;
import com.example.demo.petRepository.PetRepository;

@Service
public class PetServiceImpl implements PetService{
	@Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPetsByCategory(String category) {
        return petRepository.findByCategory(category);
    }

    public List<Pet> getPetsByCategoryAndColor(String category, String color) {
        return petRepository.findByCategoryAndColor(category, color);
    }

    public Pet savePet(Pet pet) {
    	 return petRepository.save(pet);
		
    }

    public boolean deletePet(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
            return true;
        }
        return false;
    }

	
    public Optional<Pet> getById(Long id) {
        
        
            return petRepository.findById(id);
        
		
    }


}
