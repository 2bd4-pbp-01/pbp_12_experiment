# Membandingkan Algoritma Rate Limiting
## 1. Identifikasi Masalah
Dalam pengembangan aplikasi web, terutama aplikasi yang menghadapi beban tinggi, penting untuk mengelola jumlah permintaan yang dapat diterima server dalam jangka waktu tertentu. Tanpa pengelolaan yang tepat, server bisa mengalami overload, menurunnya performa, atau bahkan mengalami crash. Rate limiting adalah teknik yang digunakan untuk membatasi jumlah permintaan dari pengguna atau aplikasi dalam periode waktu tertentu, untuk melindungi server dan menjaga kualitas layanan.

## 2. Konsep dari tiap Algoritma
        ### - Token Bucket
              Token Bucket adalah algoritma rate limiting yang menggunakan prinsip penyimpanan token untuk mengatur aliran                    permintaan. Setiap permintaan memerlukan token untuk diproses. Token ditempatkan dalam sebuah "bucket" yang                     secara berkala diisi ulang dengan kecepatan tetap. Ketika bucket penuh, token yang berlebih akan dibuang. Jika                  bucket kosong dan ada permintaan, permintaan tersebut ditunda atau ditolak, tergantung pada kebijakan yang                      diterapkan. Konsep ini memungkinkan lonjakan mendadak dalam permintaan selama ada token yang tersedia,                          menjadikannya ideal untuk aplikasi dengan pola lalu lintas yang tidak teratur dan memerlukan fleksibilitas dalam                menangani bursty traffic.
               
