package com.backendfiscale.backendfiscale.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "societes")
public class Societe extends User {

    @NotBlank(message = "Raison sociale requise")
    @Size(max = 200)
    String raisonSociale;

    @Size(max = 500)
    String activitesExercees;

    // dateDebutActivite en LocalDate (meilleur pour les calculs)
    LocalDate dateDebutActivite;

    @Size(max = 200)
    String typeActiviteEconomique; // ANSEJ, CNAC, artisanat, etc.

    @Size(max = 250)
    String adresseLieuExercice;

    @Size(max = 250)
    String adresseDomicile;

    // SIREN : contrainte d'unicité et format numérique (ex: 9 chiffres)
    @NotBlank(message = "SIREN requis")

    @Column(name = "siren", nullable = false, unique = true )
    String siren;

    // NIF (numéro d'identification fiscale) — si applicable
    @Size(max = 50)
    @Column(name = "nif", unique = false)
    String nif;

    @Size(max = 100)
    String nin;   // Numéro d’Identification National

    @Size(max = 100)
    String numeroArticleImposition;

    @Size(max = 50)
    String numeroPatente;

    @Size(max = 50)
    String numeroCarteArtisan;


    @ManyToOne
    @JoinColumn(name = "comptable_id")
    Comptable comptable;


    @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
    Set<DeclarationFiscale> declarations = new HashSet<>();

    @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
    Set<RegistreAchat> achats = new HashSet<>();

    @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
    Set<RegistreVente> ventes = new HashSet<>();

    @OneToMany(mappedBy = "societe", cascade = CascadeType.ALL)
    Set<Historique> historiques = new HashSet<>();
}