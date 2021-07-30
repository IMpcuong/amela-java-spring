package com.tutorial.service;

import com.tutorial.model.Customer;

public interface ICustomerService extends IGeneralService<Customer> {

    boolean insertWithStoredProcedure(Customer customer);
}
