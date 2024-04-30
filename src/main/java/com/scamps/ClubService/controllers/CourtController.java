package com.scamps.ClubService.controllers;

import com.scamps.ClubService.controllers.generics.GenericController;
import com.scamps.ClubService.models.Court;
import com.scamps.ClubService.models.services.CourtService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/court")
public class CourtController extends GenericController<Court> {

    @Autowired
    public CourtController(CourtService service, final Logger logger) {
        super(service, logger);
    }
}
