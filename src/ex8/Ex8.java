/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex8;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Address;
import model.Customer;

/**
 *
 * @author alongkornvanzoh
 */
public class Ex8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//      createData();
//      printAllCustomer();
//      printCustomerByCity("Bangkok");
    }

    public static void createData() {
        Customer customerOne = new Customer(1L, "John", "Henry", "jh@mail.com");
        Address addressOne = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "10900", "TH");
        addressOne.setCustomerFk(customerOne);
        customerOne.setAddressId(addressOne);

        Customer customerTwo = new Customer(2L, "Marry", "Jane", "mj@mail.com");
        Address addressTwo = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "10900", "TH");
        addressTwo.setCustomerFk(customerTwo);
        customerTwo.setAddressId(addressTwo);

        Customer customerThree = new Customer(3L, "Peter", "Parker", "pp@mail.com");
        Address addressThree = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "20900", "TH");
        addressThree.setCustomerFk(customerThree);
        customerThree.setAddressId(addressThree);

        Customer customerFour = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");
        Address addressFour = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "30500", "TH");
        addressFour.setCustomerFk(customerFour);
        customerFour.setAddressId(addressFour);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ex8PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(customerOne);
            em.persist(addressOne);
            em.flush();
            em.persist(customerTwo);
            em.persist(addressTwo);
            em.flush();
            em.persist(customerThree);
            em.persist(addressThree);
            em.flush();
            em.persist(customerFour);
            em.persist(addressFour);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void printAllCustomer() {
        List<Address> address = getAllAddress();
        for (Address a : address) {
            Customer customer = a.getCustomerFk();
            System.out.println("First Name: " + customer.getFirstname());
            System.out.println("Last Name: " + customer.getLastname());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Street: " + a.getStreet());
            System.out.println("City: " + a.getCity());
            System.out.println("Country:: " + a.getCountry());
            System.out.println("Zip Code: " + a.getZipcode());
            System.out.println("--------------------");
            System.out.println("--------------------");
        }
    }

    public static void printCustomerByCity(String city) {
        List<Address> addressBangkok = getAddressByBangkokCity(city);
        for (Address a : addressBangkok) {
            Customer customer = a.getCustomerFk();
            System.out.println("First Name: " + customer.getFirstname());
            System.out.println("Last Name: " + customer.getLastname());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Street: " + a.getStreet());
            System.out.println("City: " + a.getCity());
            System.out.println("Country:: " + a.getCountry());
            System.out.println("Zip Code: " + a.getZipcode());
            System.out.println("--------------------");
            System.out.println("--------------------");
        }
    }

    public static List<Address> getAllAddress() {
        EntityManager em = Persistence.createEntityManagerFactory("Ex8PU").createEntityManager();
        List<Address> address = em.createQuery("SELECT a FROM Address a ORDER BY a.id").getResultList();
        em.close();
        return address;
    }

    public static List<Address> getAddressByBangkokCity(String city) {
        EntityManager em = Persistence.createEntityManagerFactory("Ex8PU").createEntityManager();
        List<Address> address = em.createQuery("SELECT a FROM Address a WHERE a.city = ?1 ORDER BY a.id").setParameter(1, city).getResultList();
        em.close();
        return address;
    }

}
