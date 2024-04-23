package br.com.gurodriguesdev.paymentsmicroservice.transaction;

import org.springframework.data.repository.ListCrudRepository;

public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
