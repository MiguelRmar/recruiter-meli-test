package com.meli.recruiter.service;

import com.meli.recruiter.domain.Dna;
import com.meli.recruiter.dto.dnaDTO;
import com.meli.recruiter.dto.statsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meli.recruiter.repository.IDnaRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class DnaService implements IDnaService{

    @Autowired
    IDnaRepository dnaRepository;

    private ArrayList<String> sequence;
    private int row = 0;
    private int col = 0;
    private int sec = 0;

    @Override
    public boolean isMutant(dnaDTO dna) {
        boolean response = false;
        String valueDna = dna.getDna().toString();
        if (existDna(valueDna)){
            response = !dnaRepository.isHuman(valueDna);
        } else {
            response = validateDna(dna);
        }
        return response;
    }

    @Override
    public statsDTO getStats() {
        Integer countHumans = dnaRepository.humans();
        Integer countMutants = dnaRepository.mutants();
        Float ratio = Float.valueOf(countMutants)/Float.valueOf(countHumans);
        statsDTO stats = new statsDTO(countMutants, countHumans, ratio);
        return stats;
    }

    private boolean existDna(String dna) {
        return dnaRepository.existsByDna(dna);
    }

    private boolean validateDna(dnaDTO dna) {
        row = 0;
        col = 0;
        sec = 0;
        boolean response = false;
        sequence = dna.getDna();
        for (int i = 0; i < sequence.size(); i++ ) {
            String word = sequence.get(i);
            row = i;
            for (int j = 0; j < word.length() ; j++) {
                col = j;
                if(validateSequence(word.charAt(j), row, col, 1, 1)) {
                    sec += 1;
                }
                if (sec == 2) {
                    response = true;
                    j = word.length();
                }
            }
            if(response) i = sequence.size();
        }
         // Save into table
         Dna table = new Dna();
         table.setDna(dna.getDna().toString());
         table.setIsHuman(!response);
         dnaRepository.save(table);
        return response;
    }

    private boolean validateSequence(char a, int i, int j, int size, int dir) {
        char b = ' ';
        while (dir < 5) {
            switch (dir) {
                case 2: {
                    i+=1;
                    break;
                }
                case 3: {
                    i+=1;
                    j+=1;
                    break;
                }
                case 4: {
                    i+=1;
                    j-=1;
                    break;
                }
                default: {
                    j+=1;
                    break;
                }
            }
            try {
                b = sequence.get(i).charAt(j);
            } catch (Exception err) {
                b = ' ';
            }
            if (size == 4) {
                return true;
            } else if(a==b) {
                return validateSequence(a, i, j, size+1, dir);
            } else {
                dir+=1;
                size=1;
                return validateSequence(a, row, col, size, dir);
            }
        }
        return false;
    }
}
