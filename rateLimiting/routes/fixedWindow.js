const express = require('express');
const rateLimit = require('express-rate-limit');

const router = express.Router();

// Implementasi Fixed Window
const fixedWindowLimiter = rateLimit({
  windowMs: 60 * 1000, // 1 menit
  max: 5, // Maksimal 5 permintaan per menit
  message: 'Terlalu banyak permintaan, coba lagi setelah 1 menit.'
});

router.use(fixedWindowLimiter);

router.get('/', (req, res) => {
  res.send('Anda berhasil mengakses dengan Fixed Window Rate Limiting!');
});

module.exports = router;
