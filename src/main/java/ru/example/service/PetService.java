package ru.example.service;

import ru.example.dto.PetDto;
import ru.example.mapper.PetMapper;
import ru.example.model.Pet;
import ru.example.repository.PetRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetService {
    private final PetRepository petRep = new PetRepository();
    private final PetMapper petMap = new PetMapper();

    public PetDto save(PetDto petDto) throws SQLException {
        petRep.save(petMap.toEntity(petDto));

        return petDto;
    }

    public Pet getById(Integer id) throws SQLException {
        Pet pet = petRep.findById(id);

        return (pet != null) ? petMap.toDto(pet) : null;
    }

    public List<Pet> getAll() throws SQLException {
        List<Pet> owner = petRep.findAll();
        List<Pet> userResponses = new ArrayList<>();

        for (Pet p : owner) {
            userResponses.add(petMap.toDto(p));

        }

        return userResponses;
    }

    public PetDto update(PetDto petDto) throws SQLException {
        petRep.update(petMap.toEntity(petDto));

        return petDto;
    }

    public void delete(Integer id) throws SQLException {
        petRep.delete(id);
    }
}
