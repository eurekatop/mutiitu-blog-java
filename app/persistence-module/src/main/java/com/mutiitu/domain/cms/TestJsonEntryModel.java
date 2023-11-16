package com.mutiitu.domain.cms;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.OriginalStates;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;
import lombok.EqualsAndHashCode;

import com.google.gson.annotations.Expose;
import com.mutiitu.persistence.BaseModel;

@Table(name = "TestJsonEntry")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class TestJsonEntryModel extends BaseModel {
    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Expose
    @Column(name = "JSON")
    JsonDomain<TestJsonContent> jsonDomain;

    @Transient
    @OriginalStates
    CmsEntryModel originalStates;
}
