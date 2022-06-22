package com.lopezino.systm.repository;

import com.lopezino.systm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Customer c " +
            "WHERE c.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    //JPQL with named parameters
    @Query("SELECT c from Customer c where c.firstName=:firstname")
    List<Customer> findByFirstName(@Param("firstname") String firstName);

    //JPQL with index parameters
    @Query("SELECT c from Customer c where c.firstName=?1")
    List<Customer> findByFirstNamewithIndexParam(String firstName);

    //Native SQL with named parameters
    @Query(value = "SELECT * from Customer c where c.lastname=:lastname", nativeQuery = true)
    List<Customer> findByLastName(@Param("lastname") String lastname);

    //Native SQL with Index parameters
    @Query(value = "SELECT * from Customer c where c.lastname= ?1", nativeQuery = true)
    List<Customer> findByLastNamewithIndexParam(String lastname);

    @Query(value="SELECT c FROM Customer c WHERE c.lastName=?1")
    List<Customer> getCustomerInfoByLastName(String lastName);

    @Query(value="SELECT c FROM Customer c WHERE c.firstName=?1 AND email=?2")
    List<Customer> findByFirstNameAndEmail(String firstName,String email);
}
