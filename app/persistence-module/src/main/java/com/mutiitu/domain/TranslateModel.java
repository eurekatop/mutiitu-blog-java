package com.mutiitu.domain;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import lombok.EqualsAndHashCode;
import com.mutiitu.persistence.BaseModel;


@Table(name = "translate")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class TranslateModel extends BaseModel {
    @Id
    String id;

    String language;

    String value;
}