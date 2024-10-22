package com.example.asociaciones.repositories;

import com.example.asociaciones.entities.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
