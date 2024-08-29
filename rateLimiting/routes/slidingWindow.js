const express = require('express');
const rateLimit = require('express-rate-limit');

const router = express.Router();

// Implementasi Sliding Window
const slidingWindowLimiter = rateLimit({
  windowMs: 60 * 1000, // 1 menit
  max: 5, // Maksimal 5 permintaan per menit
  message: 'Terlalu banyak permintaan, coba lagi setelah 1 menit.',
  keyGenerator: (req) => req.ip, // Menggunakan IP sebagai kunci untuk sliding window
  handler: (req, res, next, options) => {
    res.status(options.statusCode).send(options.message);
  }
});

router.use(slidingWindowLimiter);

router.get('/', (req, res) => {
  res.send('Anda berhasil mengakses dengan Sliding Window Rate Limiting!');
});

module.exports = router;
