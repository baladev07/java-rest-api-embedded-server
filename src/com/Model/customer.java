package com.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer_table")
public class customer {
    @Id
    @Column(name="customer_id")
    public int customer_id;
    @Column(name="customer_name")
    public String customer_name;
    @Column(name="customer_product")
    public String customer_product;

    public customer()
    {

    }
    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_project() {
        return customer_product;
    }

    public void setCustomer_project(String customer_project) {
        this.customer_product = customer_project;
    }
}
