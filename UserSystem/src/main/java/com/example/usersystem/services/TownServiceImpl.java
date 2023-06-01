package com.example.usersystem.services;

import com.example.usersystem.entities.Town;
import com.example.usersystem.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void saveTown(Town town) {
        this.townRepository.save(town);
    }
}
