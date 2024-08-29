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

  // Method untuk menghitung durasi penerbangan
  public calculateFlightDuration(flight: Flight): string {
    // WARN: Menghitung selisih setelah mengonversi ke waktu lokal akan
    // memberikan hasil yang tidak akurat karena perbedaan timezone
    // TODO: Langsung menghitung selisih (dalam bentuk epoch time) tanpa
    // mengonversikan dulu ke waktu lokal agar tidak terpengaruh timezone
    const departureTimeLocal = new Date(flight.departureTime).toLocaleString("en-US", { timeZone: flight.originTimezone });
    const arrivalTimeLocal = new Date(flight.arrivalTime).toLocaleString("en-US", { timeZone: flight.destinationTimezone });
    const durationInMillis = new Date(arrivalTimeLocal).getTime() - new Date(departureTimeLocal).getTime();

    const durationInHours = Math.floor(durationInMillis / (1000 * 60 * 60));
    const durationInMinutes = Math.floor((durationInMillis % (1000 * 60 * 60)) / (1000 * 60));
    return `${durationInHours}h ${durationInMinutes}m`;
  }

  // WARN: Kode dibawah merupkan method yang salah
  // toLocaleString() tanpa menentukan timeZone akan
  // mengembalikan waktu lokal dari lingkungan server
  // atau komputer, yang bisa berbeda dengan timezone pengguna.
  // TODO: Membuat method terpisah untuk mengonversi epoch time ke waktu lokal pengguna
  public getBookingInfo(booking: Booking): string {
    const departureTime = new Date(booking.flight.departureTime);
    const arrivalTime = new Date(booking.flight.arrivalTime);

    return `Departure: ${departureTime.toLocaleString()} (${booking.flight.origin})
Arrival: ${arrivalTime.toLocaleString()} (${booking.flight.destination})`;
  }
}

