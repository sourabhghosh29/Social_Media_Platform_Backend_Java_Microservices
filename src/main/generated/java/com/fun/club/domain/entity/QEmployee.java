package com.fun.club.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = 571744043L;

    public static final QEmployee employee = new QEmployee("employee");

    public final StringPath cakeFlavour = createString("cakeFlavour");

    public final NumberPath<Long> contact = createNumber("contact", Long.class);

    public final DateTimePath<java.util.Date> dob = createDateTime("dob", java.util.Date.class);

    public final StringPath employeeId = createString("employeeId");

    public final StringPath employeeName = createString("employeeName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath initiatives = createString("initiatives");

    public final StringPath interests = createString("interests");

    public QEmployee(String variable) {
        super(Employee.class, forVariable(variable));
    }

    public QEmployee(Path<? extends Employee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(Employee.class, metadata);
    }

}

