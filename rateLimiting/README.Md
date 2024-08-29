# Membandingkan Algoritma Rate Limiting

## 1. Identifikasi Masalah
Dalam pengembangan aplikasi web, terutama aplikasi yang menghadapi beban tinggi, penting untuk mengelola jumlah permintaan yang dapat diterima server dalam jangka waktu tertentu. Tanpa pengelolaan yang tepat, server bisa mengalami overload, menurunnya performa, atau bahkan mengalami crash. Rate limiting adalah teknik yang digunakan untuk membatasi jumlah permintaan dari pengguna atau aplikasi dalam periode waktu tertentu, untuk melindungi server dan menjaga kualitas layanan.

## 2. Konsep dari tiap Algoritma
      ### - Token Bucket
Token Bucket adalah algoritma rate limiting yang menggunakan prinsip penyimpanan token untuk mengatur aliran               permintaan. Setiap permintaan memerlukan token untuk diproses. Token ditempatkan dalam sebuah "bucket" yang                  secara berkala diisi ulang dengan kecepatan tetap. Ketika bucket penuh, token yang berlebih akan dibuang. Jika               bucket kosong dan ada permintaan, permintaan tersebut ditunda atau ditolak, tergantung pada kebijakan yang                    diterapkan. Konsep ini memungkinkan lonjakan mendadak dalam permintaan selama ada token yang tersedia,                        menjadikannya ideal untuk aplikasi dengan pola lalu lintas yang tidak teratur dan memerlukan fleksibilitas dalam             menangani bursty traffic.

      ### - Leaky Bucket
Leaky Bucket adalah algoritma rate limiting yang mengatur aliran permintaan dengan konsep bucket yang memiliki lubang. Permintaan dikumpulkan dalam bucket yang memiliki kecepatan "kebocoran" tetap, yaitu kecepatan pemrosesan permintaan. Jika bucket penuh, permintaan tambahan akan ditunda hingga ada ruang yang tersedia. Bucket yang bocor secara konsisten mengeluarkan permintaan dengan kecepatan tetap, memberikan aliran permintaan yang stabil dan dapat diprediksi. Ini mencegah lonjakan mendadak dan menjaga aliran lalu lintas yang stabil, ideal untuk aplikasi yang tidak dapat menangani lonjakan besar dalam permintaan.

      ### - Fixed Window
Fixed Window adalah algoritma rate limiting yang membatasi jumlah permintaan yang dapat dilakukan dalam jangka waktu tetap. Dalam sistem ini, waktu dibagi menjadi jendela waktu tetap (misalnya, satu menit). Setiap jendela waktu memiliki batasan jumlah permintaan yang dapat dilakukan. Jika batas terlampaui dalam jendela waktu tersebut, permintaan tambahan akan ditolak hingga jendela waktu berikutnya dimulai. Algoritma ini mudah diimplementasikan dan efektif untuk aplikasi dengan pola lalu lintas yang stabil, tetapi bisa kurang fleksibel untuk aplikasi dengan lonjakan permintaan yang mendekati batas jendela waktu.

      ### - Fixed Window
Sliding Window adalah algoritma rate limiting yang memantau permintaan dalam jendela waktu yang bergerak. Alih-alih membatasi permintaan dalam jendela waktu tetap, algoritma ini menggunakan periode waktu yang terus bergerak, sehingga setiap permintaan diperhitungkan berdasarkan jendela waktu yang dinamis. Ini memungkinkan kontrol yang lebih granular dan dinamis atas aliran permintaan, menghindari lonjakan tajam yang bisa terjadi pada batas jendela tetap. Sliding Window memberikan fleksibilitas lebih dalam menangani pola permintaan yang fluktuatif, namun dengan kompleksitas implementasi yang lebih tinggi.

Perbandingan Keefektifan
Token Bucket: Paling efektif dalam menangani lonjakan mendadak karena token yang tersedia memungkinkan penundaan atau pemrosesan permintaan dalam batas token. Ideal untuk sistem yang memerlukan penanganan burst traffic.
Leaky Bucket: Baik untuk menjaga aliran permintaan yang stabil dan mencegah lonjakan besar. Kurang fleksibel dalam menghadapi lonjakan mendadak.
Fixed Window: Efektif dalam skenario dengan pola lalu lintas yang stabil, tetapi dapat menyebabkan lonjakan tajam pada awal jendela waktu baru.
Sliding Window: Memberikan kontrol granular terbaik dan menghindari lonjakan tajam. Cocok untuk aplikasi dengan pola lalu lintas fluktuatif tetapi lebih kompleks untuk diimplementasikan.

Kesimpulan
Token Bucket adalah pilihan terbaik untuk aplikasi yang menghadapi lonjakan mendadak karena kemampuannya untuk menangani burst traffic dengan memanfaatkan token yang tersedia.
Leaky Bucket adalah pilihan yang baik untuk menjaga aliran permintaan yang stabil dan menghindari lonjakan mendadak.
Fixed Window cocok untuk aplikasi dengan pola lalu lintas yang cukup stabil, meskipun dapat menyebabkan lonjakan tajam.
Sliding Window adalah yang paling dinamis dan granular, ideal untuk aplikasi dengan pola lalu lintas yang sangat fluktuatif, tetapi memerlukan pengelolaan yang lebih kompleks.

               
