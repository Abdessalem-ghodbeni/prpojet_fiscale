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
@Table(name = "registre_achats")
public class RegistreAchat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fournisseur;

    String description;

    double montantHT;

    double tva;

    double montantTTC;

    LocalDate dateAchat;

    String numeroFacture;

    @ManyToOne
    @JoinColumn(name = "societe_id")
    Societe societe;
}