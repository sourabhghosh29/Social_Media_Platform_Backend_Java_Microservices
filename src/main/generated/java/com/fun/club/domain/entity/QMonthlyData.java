package com.fun.club.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMonthlyData is a Querydsl query type for MonthlyData
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMonthlyData extends EntityPathBase<MonthlyData> {

    private static final long serialVersionUID = -496910406L;

    public static final QMonthlyData monthlyData = new QMonthlyData("monthlyData");

    public final NumberPath<Long> contribution_amount = createNumber("contribution_amount", Long.class);

    public final NumberPath<Long> expense_amount = createNumber("expense_amount", Long.class);

    public final NumberPath<Long> month_id = createNumber("month_id", Long.class);

    public final StringPath month_name = createString("month_name");

    public final NumberPath<Long> view_id = createNumber("view_id", Long.class);

    public final NumberPath<Double> years = createNumber("years", Double.class);

    public QMonthlyData(String variable) {
        super(MonthlyData.class, forVariable(variable));
    }

    public QMonthlyData(Path<? extends MonthlyData> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMonthlyData(PathMetadata metadata) {
        super(MonthlyData.class, metadata);
    }

}

