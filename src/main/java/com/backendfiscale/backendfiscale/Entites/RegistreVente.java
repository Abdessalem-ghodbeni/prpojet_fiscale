package com.backendfiscale.backendfiscale.Entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "registre_ventes")
public class RegistreVente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String client;

    String description;

    double montantHT;

    double tva;

    double montantTTC;

    LocalDate dateVente;

    String numeroFacture;

    @ManyToOne
    @JoinColumn(name = "societe_id")
    Societe societe;
}