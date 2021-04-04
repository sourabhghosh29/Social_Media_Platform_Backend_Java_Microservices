package com.fun.club.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QExpense is a Querydsl query type for Expense
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExpense extends EntityPathBase<Expense> {

    private static final long serialVersionUID = 1441532987L;

    public static final QExpense expense = new QExpense("expense");

    public final NumberPath<Long> expenseAmount = createNumber("expenseAmount", Long.class);

    public final DateTimePath<java.util.Date> expenseDate = createDateTime("expenseDate", java.util.Date.class);

    public final NumberPath<Long> expenseId = createNumber("expenseId", Long.class);

    public final StringPath reason = createString("reason");

    public QExpense(String variable) {
        super(Expense.class, forVariable(variable));
    }

    public QExpense(Path<? extends Expense> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExpense(PathMetadata metadata) {
        super(Expense.class, metadata);
    }

}

