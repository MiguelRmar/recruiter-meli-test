package com.meli.recruiter.controller;

import com.meli.recruiter.dto.dnaDTO;
import com.meli.recruiter.dto.statsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.meli.recruiter.service.DnaService;
import com.meli.recruiter.service.IDnaService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*",methods= {RequestMethod.GET,RequestMethod.POST})
public class DnaController {
    @Autowired
    IDnaService dnaService = new DnaService();

    @RequestMapping(method = RequestMethod.POST , path = "/mutant")
    public ResponseEntity<?> validateDna(@Valid @RequestBody dnaDTO dna) {
        try {
            if (dnaService.isMutant(dna)) {
                return new ResponseEntity<>("Es mutante", HttpStatus.OK);
            }
            return new ResponseEntity<>("No es mutante", HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            return new ResponseEntity<>("Hubo un falló en la aplicación", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<statsDTO> getStats() {
        return new ResponseEntity<>(dnaService.getStats(), HttpStatus.OK);
    }
}
