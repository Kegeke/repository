package ru.example.mapper;

import ru.example.dto.PetDto;
import ru.example.model.Pet;

public class PetMapper {
    public Pet toEntity(PetDto petDto) {
        return new Pet.PetBuilder()
                .setId(petDto.getId())
                .setOwner(petDto.getOwner())
                .setName(petDto.getName())
                .build();
    }

    public Pet toDto(Pet pet) {
        return new Pet.PetBuilder()
                .setId(pet.getId())
                .setOwner(pet.getOwner())
                .setName(pet.getName())
                .build();
    }
}
