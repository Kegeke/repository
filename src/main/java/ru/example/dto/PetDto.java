package ru.example.dto;

import ru.example.model.Owner;
import ru.example.model.Pet;

public class PetDto {
    private Integer id;
    private String name;
    private Owner owner;

    public PetDto(Pet pet) {
        this.id = pet.getId();
        this.name = pet.getName();
        this.owner = pet.getOwner();
    }

    public PetDto(Integer id, String name, Owner owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
