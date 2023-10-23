package com.mutiitu.domain.resume;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import com.google.gson.annotations.Expose;
import com.mutiitu.persistence.BaseModel;

import lombok.EqualsAndHashCode;

@Table(name = "Employer")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class EmployerModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Expose
    public String name;

    @Expose
    public String address;
}
