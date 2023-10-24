package com.mutiitu.domain;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import lombok.EqualsAndHashCode;
import com.mutiitu.persistence.BaseModel;


@Table(name = "ContactMe")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class ContactMeModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    String subject;

    String message;

    String email;

}