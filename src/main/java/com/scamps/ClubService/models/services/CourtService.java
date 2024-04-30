package com.scamps.ClubService.models.services;

import com.scamps.ClubService.models.Court;
import com.scamps.ClubService.models.services.generics.GenericService;
import com.scamps.ClubService.repositories.ICourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourtService extends GenericService<Court> {

    @Autowired
    public CourtService(ICourtRepository courtRepository) {
        super(courtRepository);
    }
}
