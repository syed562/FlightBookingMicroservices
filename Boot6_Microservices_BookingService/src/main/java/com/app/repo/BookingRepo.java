package com.app.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.models.Booking;

@Repository
public interface BookingRepo  extends CrudRepository<Booking,Long>{
Optional<Booking>findByPnr(String pnr);
}
