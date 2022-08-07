package com.meli.recruiter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class statsDTO {
    public Integer count_mutant_dna;
    public Integer count_human_dna;
    public Float ratio;
}
