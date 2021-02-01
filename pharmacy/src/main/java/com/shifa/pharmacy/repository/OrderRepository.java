package com.shifa.pharmacy.repository;

import com.shifa.pharmacy.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
