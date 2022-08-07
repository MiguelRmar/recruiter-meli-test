package com.meli.recruiter.domain;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Dna")
public class Dna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdDna")
    private Integer id;

    @Column(name="dna")
    private String dna;

    @Column(name="isHuman")
    private Boolean isHuman;
}
