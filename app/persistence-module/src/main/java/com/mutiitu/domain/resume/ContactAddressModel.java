package com.mutiitu.domain.resume;

import java.util.ArrayList;
import java.util.List;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;

import com.google.gson.annotations.Expose;
import com.mutiitu.persistence.BaseModel;

import lombok.EqualsAndHashCode;

@Table(name = "ContactAddress")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class ContactAddressModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Expose
    String city;

    @Expose
    String phoneNumber;
    
    @Expose
    String street;
//
     @Expose
     String email;

     @Expose
     @Transient
     List<OnlineProfileModel> onlineProfiles = new ArrayList<>();
}
