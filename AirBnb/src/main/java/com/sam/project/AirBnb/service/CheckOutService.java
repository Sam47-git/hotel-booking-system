package com.sam.project.AirBnb.service;

import com.sam.project.AirBnb.entities.Booking;

public interface CheckOutService {

    String getCheckOutSession(Booking booking, String successUrl, String failureUrl);
}
