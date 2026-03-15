package com.backendfiscale.backendfiscale.Repository;

import com.backendfiscale.backendfiscale.Entites.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin,Long> {
}
