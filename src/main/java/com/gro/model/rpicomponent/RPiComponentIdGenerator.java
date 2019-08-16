package com.gro.model.rpicomponent;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class RPiComponentIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        final String CALL_PROC_QUERY = "{? = call sequence_nextval()}";
        int nextValue = 0;
        try {
            final CallableStatement callableStmt = session.connection()
                .prepareCall(CALL_PROC_QUERY);
            callableStmt.registerOutParameter(1, java.sql.Types.BIGINT);
            callableStmt.execute();
            nextValue = callableStmt.getInt(1);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return nextValue;
    }
}
