import { type User } from "./user";
import { type Flight } from "./flight";

export interface Booking {
  bookingId: number;
  user: User;
  flight: Flight;
  bookingTime: number; // epoch time in milliseconds
}
