import { type User } from "../types/user";
import { type Flight } from "../types/flight";
import { type Booking } from "../types/booking";

export class FlightBookingSystem {
  private bookings: Booking[] = [];

  // Method untuk membuat pemesanan baru
  public createBooking(user: User, flight: Flight): Booking {
    const bookingTime = Date.now();
    const booking: Booking = {
      bookingId: this.bookings.length + 1,
      user,
      flight,
      bookingTime
    };

    this.bookings.push(booking);
    return booking;
  }

  // Method untuk mengonversi epoch time ke waktu lokal pengguna
  public convertToUserTimezone(epochTime: number, timezone: string): string {
    const date = new Date(epochTime);
    return date.toLocaleString("en-US", { timeZone: timezone });
  }

  // Method untuk menghitung durasi penerbangan
  public calculateFlightDuration(flight: Flight): string {
    const durationInMillis = flight.arrivalTime - flight.departureTime;

    const durationInHours = Math.floor(durationInMillis / (1000 * 60 * 60));
    const durationInMinutes = Math.floor((durationInMillis % (1000 * 60 * 60)) / (1000 * 60));
    return `${durationInHours}h ${durationInMinutes}m`;
  }

  // Method untuk mendapatkan informasi pemesanan dengan waktu lokal pengguna
  public getBookingInfo(booking: Booking): string {
    const departureTimeLocal = this.convertToUserTimezone(booking.flight.departureTime, booking.user.timezone);
    const arrivalTimeLocal = this.convertToUserTimezone(booking.flight.arrivalTime, booking.user.timezone);
    const duration = this.calculateFlightDuration(booking.flight);

    return `Booking ID: ${booking.bookingId}
User: ${booking.user.name}
Flight: ${booking.flight.flightNumber}
Departure: ${departureTimeLocal} (${booking.flight.origin})
Arrival: ${arrivalTimeLocal} (${booking.flight.destination})
Duration: ${duration}`;
  }
}

