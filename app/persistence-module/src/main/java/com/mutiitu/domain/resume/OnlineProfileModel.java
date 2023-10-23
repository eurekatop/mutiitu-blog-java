package com.mutiitu.domain.resume;

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

@Table(name = "OnlineProfile")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class OnlineProfileModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Expose
    String type;
 
    @Expose
    String profileName;
    
    @Expose
    String url;
 
    @Transient
    ResumeModel contact;
}
