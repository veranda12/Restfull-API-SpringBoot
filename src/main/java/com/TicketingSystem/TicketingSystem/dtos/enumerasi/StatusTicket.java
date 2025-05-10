package com.TicketingSystem.TicketingSystem.dtos.enumerasi;

public enum StatusTicket {
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CENCELED("CENCELED");

    private String statusTicket;

    StatusTicket(String statusTicket) {this.statusTicket = statusTicket;}

    public String getStatusTicket() {return statusTicket;}
}
