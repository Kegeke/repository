package ru.example.dto;

import ru.example.model.Owner;
import ru.example.model.Pet;

public class OwnerDto {
    private Integer id;
    private String name;
    private Pet pet;

    public OwnerDto(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.pet = owner.getPet();
    }

    public OwnerDto(Integer id, String name, Pet pet) {
        this.id = id;
        this.name = name;
        this.pet = pet;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}

