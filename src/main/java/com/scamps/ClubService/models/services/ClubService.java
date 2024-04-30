package com.scamps.ClubService.models.services;

import com.scamps.ClubService.models.Club;
import com.scamps.ClubService.models.services.generics.GenericService;
import com.scamps.ClubService.repositories.IClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService extends GenericService<Club> {

    @Autowired
    public ClubService(IClubRepository clubRepository) {
        super(clubRepository);
    }
}
