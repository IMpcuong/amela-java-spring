package com.tutorial.service.customer;

import com.tutorial.model.Customer;
import com.tutorial.model.Province;
import com.tutorial.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }

    @Override
    public Page<Customer> findAllPagingAndSorting(int pageNum,
                                                  String sortField,
                                                  String sortDir,
                                                  Pageable pageable) {
        int pageSize = 10;
        pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending()
        );

        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllByFirstNameContaining(String firstName,
                                                       Pageable pageable) {
        return customerRepository.findAllByFirstNameContaining(firstName, pageable);
        // return new PageImpl<>(customers.getContent(), pageable, customers.getTotalElements());
    }
}
