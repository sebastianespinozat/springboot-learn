package com.sebastian.springboot_difactura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
//@JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description}")
    private String description;

    @Autowired
    private List<Item> items;

    @PostConstruct
    public void init(){
        System.out.println("Creando el componente de la factura");
        client.setName(client.getName().concat(" Pepe"));
        description = description.concat(" del cliente ").concat(client.getName()).concat(" ").concat(client.getLastname());
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destruyendo el componente o bean invoice!");
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getTotal(){
//        int total = 0;
//        for(Item item: items){
//            total += item.getImporte();
//        }
        int total = items.stream()
                        .map(item -> item.getImporte())
                        .reduce(0,(sum, item)->sum+item);
        return total;
    }

}
