package com.TicketingSystem.TicketingSystem.dtos.enumerasi;

public enum StatusHistory {
    ON_TIME("ON_TIME"),
    OVERDUE("OVERDUE");

    private String statusHistory;

    StatusHistory(String statusHistory) {this.statusHistory = statusHistory;}

    public String getStatusHistory() {return statusHistory;}
}
