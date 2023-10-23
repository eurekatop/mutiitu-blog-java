package com.mutiitu.domain.resume;

import org.seasar.doma.Entity;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;

import com.mutiitu.persistence.BaseModel;

import lombok.EqualsAndHashCode;

@Table(name = "Skill")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class SkillModel extends BaseModel {
    String type;
    String tag;
    String description;
}
