package com.meli.recruiter.service;

import com.meli.recruiter.dto.dnaDTO;
import com.meli.recruiter.dto.statsDTO;

public interface IDnaService {
    boolean isMutant(dnaDTO dna);
    statsDTO getStats();
}
