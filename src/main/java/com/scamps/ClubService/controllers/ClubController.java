package com.scamps.ClubService.controllers;

import com.scamps.ClubService.controllers.generics.GenericController;
import com.scamps.ClubService.models.Club;
import com.scamps.ClubService.models.services.ClubService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/club")
public class ClubController extends GenericController<Club> {

    @Autowired
    public ClubController(ClubService service, final Logger logger) {
        super(service, logger);
    }
}
