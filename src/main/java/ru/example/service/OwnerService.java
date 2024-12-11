package ru.example.service;

import ru.example.dto.OwnerDto;
import ru.example.mapper.OwnerMapper;
import ru.example.model.Owner;
import ru.example.repository.OwnerRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerService {
    private final OwnerRepository ownerRepository = new OwnerRepository();
    private final OwnerMapper ownerMapper = new OwnerMapper();

    public OwnerDto save(OwnerDto ownerDto) throws SQLException {
        ownerRepository.save(ownerMapper.toEntity(ownerDto));

        return ownerDto;
    }

    public Owner getById(Integer id) throws SQLException {
        Owner owner = ownerRepository.findById(id);

        return (owner != null) ? ownerMapper.toDto(owner) : null;
    }

    public List<Owner> getAll() throws SQLException {
        List<Owner> owner = ownerRepository.findAll();
        List<Owner> userResponses = new ArrayList<>();

        for (Owner o : owner) {
            userResponses.add(ownerMapper.toDto(o));

        }

        return userResponses;
    }

    public OwnerDto update(OwnerDto ownerDto) throws SQLException {
        ownerRepository.update(ownerMapper.toEntity(ownerDto));

        return ownerDto;
    }

    public void delete(Integer id) throws SQLException {
        ownerRepository.delete(id);
    }
}