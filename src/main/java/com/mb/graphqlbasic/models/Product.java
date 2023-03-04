package com.mb.graphqlbasic.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    private String id;

    private String label;

    private double price;

    private int quantity;
    @ManyToOne
    private Category category;



}
