export interface Flight {
  flightNumber: string;
  departureTime: number; // epoch time in milliseconds
  arrivalTime: number; // epoch time in milliseconds
  origin: string; // Airport code (e.g., "JFK")
  destination: string; // Airport code (e.g., "LHR")
  originTimezone: string; // Timezone of origin (e.g., "America/New_York")
  destinationTimezone: string; // Timezone of destination (e.g., "Europe/London")
}
