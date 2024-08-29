import { type Flight } from "./types/flight";
import {type User } from "./types/user";
import { FlightBookingSystem } from "./problem/bookingSystem"
// import { FlightBookingSystem } from "./solution/bookingSystem"

const flight: Flight = {
    flightNumber: "AB123",
    departureTime: new Date("2024-09-01T08:00:00Z").getTime(), // UTC time
    arrivalTime: new Date("2024-09-01T12:00:00Z").getTime(), // UTC time
    origin: "JFK",
    destination: "LHR",
    originTimezone: "America/New_York",
    destinationTimezone: "Europe/London"
};

const user: User = {
    id: 1,
    name: "Alice",
    timezone: "Asia/Tokyo" // User in Tokyo
};

const bookingSystem = new FlightBookingSystem();
const booking = bookingSystem.createBooking(user, flight);

console.log(bookingSystem.getBookingInfo(booking));
