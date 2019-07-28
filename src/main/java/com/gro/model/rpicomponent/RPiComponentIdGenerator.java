package com.gro.model.rpicomponent;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RPiComponentIdGenerator implements IdentifierGenerator {

    private static final String QUERY = "{? = call sequence_nextval()}";
    private Logger log = LoggerFactory.getLogger(RPiComponentIdGenerator.class);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {

        Integer id;
        try {
            Connection connection = session.connection();
            CallableStatement statement = connection.prepareCall(QUERY);
            statement.registerOutParameter(1, java.sql.Types.INTEGER);
            statement.execute();
            id = statement.getInt(1);
        } catch (SQLException e) {
            log.error("Error getting id", e);
            throw new HibernateException(e);
        }
        return id;
    }
}
