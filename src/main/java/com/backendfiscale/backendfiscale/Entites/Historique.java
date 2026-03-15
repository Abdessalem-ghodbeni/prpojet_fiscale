package com.backendfiscale.backendfiscale.Entites;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "historiques")
public class Historique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String action;
    // CREATION_DECLARATION
    // MODIFICATION
    // PAIEMENT
    // VALIDATION

    String description;

    LocalDateTime dateAction;

    @ManyToOne
    @JoinColumn(name = "societe_id")
    Societe societe;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}