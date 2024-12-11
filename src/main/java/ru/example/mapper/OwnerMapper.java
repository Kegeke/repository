package ru.example.mapper;

import ru.example.dto.OwnerDto;
import ru.example.model.Owner;

public class OwnerMapper {
    public Owner toEntity(OwnerDto ownerDto) {
        return new Owner.OwnerBuilder()
                .setId(ownerDto.getId())
                .setPet(ownerDto.getPet())
                .setName(ownerDto.getName())
                .build();
    }

    public Owner toDto(Owner owner) {
        return new Owner.OwnerBuilder()
                .setId(owner.getId())
                .setPet(owner.getPet())
                .setName(owner.getName())
                .build();
    }
}
