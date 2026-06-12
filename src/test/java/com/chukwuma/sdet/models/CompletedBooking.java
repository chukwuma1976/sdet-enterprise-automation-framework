package com.chukwuma.sdet.models;

public class CompletedBooking extends Booking {

    private int bookingid;

    public CompletedBooking() {
        super();
    }

    public CompletedBooking(int bookingid, Booking booking) {
        super();
        this.bookingid = bookingid;
        this.setFirstname(booking.getFirstname());
        this.setLastname(booking.getLastname());
        this.setTotalprice(booking.getTotalprice());
        this.setDepositpaid(booking.isDepositpaid());
        this.setBookingdates(booking.getBookingdates());
        this.setAdditionalneeds(booking.getAdditionalneeds());
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + bookingid;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompletedBooking other = (CompletedBooking) obj;
        if (bookingid != other.bookingid)
            return false;
        return true;
    }

}
