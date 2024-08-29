const express = require('express');
const slowDown = require('express-slow-down');
const router = express.Router();

// Leaky Bucket Rate Limiting
const leakyBucketLimiter = slowDown({
  windowMs: 60 * 1000, // 1 menit
  delayMs: (req, res) => {
    // Logika penundaan berdasarkan batas bucket
    return 1000; // Penundaan tetap untuk semua permintaan
  }
});

router.use(leakyBucketLimiter);

router.get('/', (req, res) => {
  res.send('Leaky Bucket Rate Limiting');
});

module.exports = router;
