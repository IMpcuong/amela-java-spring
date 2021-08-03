package com.tutorial.service.customer;

import com.tutorial.model.Customer;
import com.tutorial.model.Province;
import com.tutorial.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService extends IGeneralService<Customer> {

    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAllPagingAndSorting(int pageNum, String sortField, String sortDir, Pageable pageable);

    Page<Customer> findAllByFirstNameContaining(String firstName, Pageable pageable);
}
