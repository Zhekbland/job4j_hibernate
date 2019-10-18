package ru.job4j.models;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Class IPAddressType does logic for convert to and from database via Hibernate.
 * Describe IP Address (IP and port).
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 18.10.2019.
 */
public class IPAddressType implements UserType, CompositeUserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{StringType.INSTANCE.sqlType(), StringType.INSTANCE.sqlType()};
    }

    @Override
    public String[] getPropertyNames() {
        return new String[]{"ip_address", "port"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{StringType.INSTANCE, StringType.INSTANCE};
    }

    /**
     * Get property from DB table.
     * @param component ipAddress.
     * @param property number of property
     * @return property
     * @throws HibernateException wrong number of property
     */
    @Override
    public Object getPropertyValue(Object component, int property) throws HibernateException {
        IPAddress ipAddress = (IPAddress) component;
        switch (property) {
            case 0:
                return ipAddress.getIp();
            case 1:
                return ipAddress.getPort();
            default:
                throw new IllegalArgumentException("Invalid property number '"
                        + property + "'");
        }
    }

    /**
     * Set value into DB table.
     * @param component ipAddress
     * @param property number of property
     * @param value of ipAddress
     * @throws HibernateException wrong number of property
     */
    @Override
    public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
        IPAddress ipAddress = (IPAddress) component;
        switch (property) {
            case 0:
                ipAddress.setIp((String) value);
                break;
            case 1:
                ipAddress.setPort((String) value);
                break;
            default:
                throw new IllegalArgumentException("Invalid property number '"
                        + property + "'");
        }
    }

    @Override
    public Class returnedClass() {
        return IPAddress.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return (x != null && y != null) && x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        if (x == null) {
            return 0;
        }
        return x.hashCode();
    }

    /**
     * Get UserType Object from DB.
     * @param rs ResultSet by table from DB.
     * @param names number of parameter.
     * @param session current session.
     * @param owner owner.
     * @return ipAddress.
     * @throws HibernateException exception.
     * @throws SQLException exception.
     */
    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
            throws HibernateException, SQLException {
        IPAddress ipAddress = new IPAddress();
        ipAddress.setIp(rs.getString(names[0]));
        ipAddress.setPort(rs.getString(names[1]));
        return ipAddress;
    }

    /**
     * Set values of UserType Object.
     * @param st PreparedStatement.
     * @param value ipAddress.
     * @param index index.
     * @param session current session.
     * @throws HibernateException exception.
     * @throws SQLException exception.
     */
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        IPAddress ipAddress = (IPAddress) value;
        if (ipAddress != null) {
            st.setString(index, ipAddress.getIp());
            st.setString(index + 1, ipAddress.getPort());
        } else {
            st.setNull(index, StringType.INSTANCE.sqlType());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        if (Objects.isNull(value)) {
            return null;
        }
        IPAddress ipAddress = new IPAddress();
        return new IPAddress(ipAddress.getIp(), ipAddress.getPort());
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object value, SharedSessionContractImplementor session) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }
}