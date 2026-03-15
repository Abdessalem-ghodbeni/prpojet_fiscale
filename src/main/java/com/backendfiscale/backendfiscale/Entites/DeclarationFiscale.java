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
@Table(name = "declarations_fiscales")
public class DeclarationFiscale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String typeDeclaration;
    // TVA / IR / IS etc

    int annee;

    String periode;
    // mois ou trimestre

    double chiffreAffaire;

    double totalAchats;

    double totalVentes;

    double tvaCollectee;

    double tvaDeductible;

    double montantImpot;

    double penalite;

    double montantTotal;

    String statut;
    // BROUILLON / SOUMISE / VALIDEE / REFUSEE

    LocalDate dateDeclaration;

    @ManyToOne
    @JoinColumn(name = "societe_id")
    Societe societe;
}