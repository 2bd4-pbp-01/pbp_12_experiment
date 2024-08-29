const express = require('express');

const app = express();

// Import routes
const fixedWindowRoute = require('./routes/fixedWindow');
const slidingWindowRoute = require('./routes/slidingWindow');
const tokenBucketRoute = require('./routes/tokenBucket');
const leakyBucketRoute = require('./routes/leakyBucket');

// Define routes
app.use('/fixed-window', fixedWindowRoute);
app.use('/sliding-window', slidingWindowRoute);
app.use('/token-bucket', tokenBucketRoute);
app.use('/leaky-bucket', leakyBucketRoute);

// Jalankan server di port 3000
app.listen(3000, () => {
  console.log('Server berjalan pada port 3000');
});
