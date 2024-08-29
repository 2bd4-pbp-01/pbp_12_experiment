const express = require('express');
const slowDown = require('express-slow-down');
const router = express.Router();

// Token Bucket Rate Limiting dengan Penundaan
const tokenBucketLimiter = slowDown({
  windowMs: 60 * 1000, // 1 menit
  delayAfter: 10, // Penundaan setelah 10 permintaan dalam 1 menit
  delayMs: () => 5000 // Penundaan 5 detik untuk setiap permintaan tambahan setelah batas
});

router.use(tokenBucketLimiter);

router.get('/', (req, res) => {
  res.send('Token Bucket with Slow Down');
});

module.exports = router;
