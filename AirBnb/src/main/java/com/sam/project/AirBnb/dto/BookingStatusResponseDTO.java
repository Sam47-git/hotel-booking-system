package com.sam.project.AirBnb.dto;

import com.sam.project.AirBnb.entities.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingStatusResponseDTO {

    private BookingStatus bookingStatus;
}
