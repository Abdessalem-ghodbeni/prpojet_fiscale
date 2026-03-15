package com.backendfiscale.backendfiscale.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "comptables")
public class Comptable extends User {

    @Size(max = 100)
    String cabinetName;

    @Size(max = 100)
    String matriculeProfessionnel; // numéro pro (optionnel), mettre unique si nécessaire

    @Size(max = 20)
    String telephoneProfessionnel;

    // Un comptable peut gérer plusieurs sociétés et une société peut avoir plusieurs comptables
    @OneToMany(mappedBy = "comptable")
    Set<Societe> societes = new HashSet<>();
}