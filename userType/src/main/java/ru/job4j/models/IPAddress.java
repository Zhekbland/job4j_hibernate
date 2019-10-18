package ru.job4j.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class IPAddress is UserType class.
 * Describe IP Address (IP and port).
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 18.10.2019.
 */
public class IPAddress implements Serializable {

    private static final long serialVersionUID = 9031872438083498896L;

    private String ip;

    private String port;

    public IPAddress() {
    }

    public IPAddress(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IPAddress ipAddress = (IPAddress) o;
        return Objects.equals(ip, ipAddress.ip) && Objects.equals(port, ipAddress.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port);
    }

    @Override
    public String toString() {
        return "IPAddress{" + "ip='" + ip + '\'' + ", port='" + port + '\'' + '}';
    }
}
