package com.example.asociaciones.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name= "client_id")
    @JoinTable(name="tbl_clientes_to_direcciones", joinColumns = @JoinColumn(name = "id_cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_direcciones"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"id_direcciones"}))
    private Set<Address> addresses;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private Set<Invoice> invoices;
    public Client() {
        addresses = new HashSet<>();
        invoices = new HashSet<>();
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private ClientDetails clientDetails;

    public Client(String name, String lastname) {
        this();
        this.name = name;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public void removeInvoice(Invoice invoice) {
        this.getInvoices().remove(invoice);
        invoice.setClient(null);
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
        clientDetails.setClient(this);
    }

    public void removeClientDetails(ClientDetails clientDetails) {
        clientDetails.setClient(null);
        this.clientDetails = null;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", invoices='" + invoices + '\'' +
                ", addresses=" + addresses + '\'' +
                ", clientDetails=" + clientDetails +
                '}';
    }


}
