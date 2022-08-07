package com.meli.recruiter.dto;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Getter
@Setter
public class dnaDTO {
    @NotNull
    public ArrayList<String> dna;
}
