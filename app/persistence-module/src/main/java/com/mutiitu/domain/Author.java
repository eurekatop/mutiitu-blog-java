package com.mutiitu.domain;

import java.util.ArrayList;
import java.util.List;

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
import com.mutiitu.persistence.BaseModel;


@Table(name = "Footer")
@Entity(metamodel = @Metamodel)
@lombok.Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer customId;

    @Column(name = "NAME")
    String name;

    @Column(name = "SURNAME")
    String surname;

    @Column(name = "AUTHOR_ID")
    Integer authorId;

    @Transient List<BlogEntry> blogEntries = new ArrayList<>();

    @OriginalStates Author originalStates;
}

