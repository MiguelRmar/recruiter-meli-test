package com.meli.recruiter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.meli.recruiter.dto.dnaDTO;
import com.meli.recruiter.dto.statsDTO;
import com.meli.recruiter.repository.IDnaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DnaServiceTest {
    @Mock
    private IDnaRepository dnaRepository;
    @InjectMocks
    private DnaService dnaService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenDnaIsHumanAndExistInDB(){
        dnaDTO dataHuman = new dnaDTO();
        ArrayList<String> dnaHuman = new ArrayList<>(Arrays.asList("AAGCGA","CGGTAC","TAATGT","AGAAGG","CACCTA","TCACTG"));
        dataHuman.setDna(dnaHuman);

        Mockito.when(dnaRepository.existsByDna(anyString())).thenReturn(true);
        Mockito.when(dnaRepository.isHuman(anyString())).thenReturn(true);

        assertEquals(false, dnaService.isMutant(dataHuman));
    }

    @Test
    public void whenDnaIsHumanAndNotExistInDB(){
        dnaDTO dataHuman = new dnaDTO();
        ArrayList<String> dnaHuman = new ArrayList<>(Arrays.asList("AAGCGA","CGGTAC","TAATGT","AGAAGG","CACCTA","TCACTG"));
        dataHuman.setDna(dnaHuman);

        Mockito.when(dnaRepository.existsByDna(anyString())).thenReturn(false);
        Mockito.when(dnaRepository.save(any())).thenReturn(any());

        assertEquals(false, dnaService.isMutant(dataHuman));
    }

    @Test
    public void whenDnaIsMutantAndExistInDB(){
        dnaDTO dataHuman = new dnaDTO();
        ArrayList<String> dnaHuman = new ArrayList<>(Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        dataHuman.setDna(dnaHuman);

        Mockito.when(dnaRepository.existsByDna(anyString())).thenReturn(true);
        Mockito.when(dnaRepository.isHuman(anyString())).thenReturn(false);

        assertEquals(true, dnaService.isMutant(dataHuman));
    }

    @Test
    public void whenDnaIsMutantAndNotExistInDB(){
        dnaDTO dataHuman = new dnaDTO();
        ArrayList<String> dnaHuman = new ArrayList<>(Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"));
        dataHuman.setDna(dnaHuman);

        Mockito.when(dnaRepository.existsByDna(anyString())).thenReturn(false);
        Mockito.when(dnaRepository.save(any())).thenReturn(any());

        assertEquals(true, dnaService.isMutant(dataHuman));
    }

    @Test
    public void whenStatsToVerifications() {
        Integer humans = 100;
        Integer mutants = 40;
        Mockito.when(dnaRepository.humans()).thenReturn(humans);
        Mockito.when(dnaRepository.mutants()).thenReturn(mutants);
        Float ratio = Float.valueOf(mutants)/Float.valueOf(humans);

        statsDTO stats = new statsDTO(mutants,humans,ratio);

        assertEquals(stats, dnaService.getStats());
    }
}
