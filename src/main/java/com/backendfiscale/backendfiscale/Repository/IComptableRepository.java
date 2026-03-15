package com.backendfiscale.backendfiscale.Repository;

import com.backendfiscale.backendfiscale.Entites.Comptable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComptableRepository extends JpaRepository<Comptable,Long> {
}
