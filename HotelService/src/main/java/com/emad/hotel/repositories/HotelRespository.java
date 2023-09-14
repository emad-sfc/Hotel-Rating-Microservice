package com.emad.hotel.repositories;

import com.emad.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRespository extends JpaRepository<Hotel,String> {
}
