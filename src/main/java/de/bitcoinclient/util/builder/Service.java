package de.bitcoinclient.util.builder;

import java.util.UUID;

public class Service {
    String name;
    int port;
    UUID uuid;
    String ip;
    boolean online;
    String type;

    public Service() {

    }

    public Service(String name, int port, String uuid, String ip, boolean online) {
        this.name = name;
        this.port = port;
        this.uuid = UUID.fromString(uuid);
        this.ip = ip;
        this.online = online;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isOnline() {
        return online;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
