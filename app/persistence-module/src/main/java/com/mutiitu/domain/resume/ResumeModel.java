package com.mutiitu.domain.resume;

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

import com.google.gson.annotations.Expose;
import com.mutiitu.persistence.BaseModel;

import lombok.EqualsAndHashCode;

@Table(name = "Resume")
@Entity(metamodel = @Metamodel)
@lombok.Data
@EqualsAndHashCode(callSuper = true)
public class ResumeModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Expose
    String fullName;


    @Expose
    @Transient
    ContactAddressModel contactAddress;

    @Expose
    @Transient
    ArrayList<String> summary = new ArrayList<>();

    @Expose
    @Transient
    List<WorkExperienceModel> workExperience = new ArrayList<>();


    @Expose
    @Transient
    List<EducationModel> education;

    @Expose
    @Transient
    List<SkillModel> skills = new ArrayList<>();


}
