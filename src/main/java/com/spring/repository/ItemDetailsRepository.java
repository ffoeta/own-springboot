package com.spring.repository;

import com.spring.model.ItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetails, UUID> {

}
