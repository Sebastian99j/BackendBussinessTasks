package com.business.rest.db.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "Enterprise")
public class Enterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enterprise_id")
    private Long id;
    @Column(name = "name", length = 25)
    private String name;
    @Column(name = "description", length = 100)
    private String description;
    @Column(name = "localization", length = 50)
    private String localization;
    @OneToMany(mappedBy = "enterprise")
    private List<Employee> employees = new ArrayList<>();

    public Enterprise(){}

    public Enterprise(Long id, String description, String localization, List<Employee> employees) {
        this.id = id;
        this.description = description;
        this.localization = localization;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
