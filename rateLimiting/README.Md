# Membandingkan Algoritma Rate Limiting

## 1. Identifikasi Masalah
Dalam pengembangan aplikasi web, terutama aplikasi yang menghadapi beban tinggi, penting untuk mengelola jumlah permintaan yang dapat diterima server dalam jangka waktu tertentu. Tanpa pengelolaan yang tepat, server bisa mengalami overload, menurunnya performa, atau bahkan mengalami crash. Rate limiting adalah teknik yang digunakan untuk membatasi jumlah permintaan dari pengguna atau aplikasi dalam periode waktu tertentu, untuk melindungi server dan menjaga kualitas layanan.

## 2. Konsep dari tiap Algoritma
***- Token Bucket***
menggunakan bucket yang diisi ulang dengan token secara berkala. Setiap permintaan memerlukan token untuk diproses. Jika bucket kosong, permintaan ditunda atau ditolak. Ini memungkinkan penanganan lonjakan mendadak dalam permintaan selama ada token yang tersedia, ideal untuk pola lalu lintas yang tidak teratur.

***- Leaky Bucket***
mengumpulkan permintaan dalam bucket yang memiliki lubang untuk mengeluarkan permintaan dengan kecepatan tetap. Jika bucket penuh, permintaan tambahan akan ditunda. Ini memastikan aliran permintaan yang stabil dan mencegah lonjakan besar, ideal untuk aplikasi yang memerlukan aliran lalu lintas yang konsisten.

***- Fixed Window***
membatasi jumlah permintaan dalam jendela waktu tetap (misalnya, satu menit). Setiap jendela waktu dihitung ulang untuk batas permintaan baru. Mudah diimplementasikan tetapi bisa menyebabkan lonjakan tajam pada awal jendela waktu baru, cocok untuk pola lalu lintas yang stabil.

***- Sliding Window***
menggunakan jendela waktu yang bergerak untuk memantau permintaan secara dinamis. Ini memberikan kontrol yang lebih halus atas aliran permintaan dan menghindari lonjakan tajam pada batas jendela tetap, cocok untuk aplikasi dengan pola lalu lintas yang fluktuatif tetapi lebih kompleks untuk diimplementasikan.


## 3. Hasil Analisis
Dari eksperimen yang telah dilakukan, ditemukan bahwa :

***a. Fixed Window***
Karena algoritma ini mereset batas permintaan dengan acuan jam, maka terdapat celah. Misalnya dalam percobaan yang saya lakukan, tiap satu sesi diatur 1 menit dengan batas permintaan sebanyak 10 kali, dan saya melakukan permintaan pada jam 10.40 lebih 55 detik, ternyata terdapat celah yang bisa membuat server kewalahan. Ini terjadi ketika saya melakukan permintaan sebanyak 10 kali sekaligus 5 detik sebelum sesi direset. Setelah waktu menunjukkan pukul 10.41, otomatis sesi baru akan muncul dan batas permintaan direset, saya bisa langsung melakukan permintaan lagi sebanyak 1 kali secara sekaligus. ini yang membuat server bisa kewalahan, sebenarnya jika dihitung waktunya. dalam 10 detik sudah ada 20 permintaan, sehingga algoritma ini kurang efektif dalam membatasi permintaan.

***b. Sliding Window***
Berbeda dengan fixed window, sliding window ini bekerja seperti stopwatch. alih-alih membatasi rate dengan patokan jam, sliding window menghitung waktu per sesinya setelah pengguna melakukan permintaan. Misalnya dalam percobaan ini, dengan tiap sesi diatur 1 menit dengan batas permintaan sebanyak 10 kali per sesinya, saya melakukan percobaan pada jam 9.30 lebih 20 detik, untuk sesi berikutnya akan direset pada jam 9.31 lebih 20 detik. Sehingga algoritma ini lebih fleksibel dan tidak ada kecacatan seperti fixed window yang terpaku pada jam. 

***c. Token Bucket***
Hasil eksperimen dengan _token bucket_ cukup berbeda dengan fixed window dan sliding window. Tidak seperti dua algoritma sebelumnya yang memakai konsep window (sesi), token bucket ini membatasi permintaan sesuai dengan token yang tersedia. Jika token dalam ember sudah habis dan belum ada token baru yang diisi, maka permintaan akan ditunda. Ember memiliki penyimpanan yang bisa dibatasi. Misalkan eksperimen saya dengan kapasitas ember 10 token, pengisian 1 token per 5 detik dan delay permintaan jika token sudah habis adalah 10 detik. Maka, 10 permintaan saya memakai token berjalan dengan lancar, tetapi saat permintaan ke 11 kemudian terjadi penundaan 10 detik. Kemudian saat delay sudah selesai, saya hanya bisa melakukan 2 permintaan karena token terisi tiap 5 detik dan delaynya 10 detik.

***d. Leaky Bucket*** 
Hasil eksperimen dengan _leaky bucket_ ini hampir mirip dengan token bucket. Tetapi bedanya pada algoritma ini yang disimpan dalam ember adalah permintaan dari pengguna, ember ini memiliki kapasitas tertentu, dan kemudian dikeluarkan satu-satu dengan teratur. 

## 4. Perbandingan Keefektifan
***Token Bucket:*** Paling efektif dalam menangani lonjakan mendadak karena token yang tersedia memungkinkan penundaan atau pemrosesan permintaan dalam batas token. Ideal untuk sistem yang memerlukan penanganan burst traffic.

***Leaky Bucket:*** Baik untuk menjaga aliran permintaan yang stabil dan mencegah lonjakan besar. Kurang fleksibel dalam menghadapi lonjakan mendadak.

***Fixed Window:*** Efektif dalam skenario dengan pola lalu lintas yang stabil, tetapi dapat menyebabkan lonjakan tajam pada awal jendela waktu baru.

***Sliding Window:*** Memberikan kontrol granular terbaik dan menghindari lonjakan tajam. Cocok untuk aplikasi dengan pola lalu lintas fluktuatif tetapi lebih kompleks untuk diimplementasikan.

## 5. Flowchart dari keempat algoritma 

***a. Fixed Window***

<img src="Gambar%20Flowchart\mermaid-diagram-2024-09-06-074227.png">

***b. Sliding Window***

<img src="Gambar%20Flowchart\mermaid-diagram-2024-09-06-074928.png">

***c. Token Bucket***

<img src="Gambar%20Flowchart\mermaid-diagram-2024-09-06-075057.png">

***d. Leaky Bucket***

<img src="Gambar%20Flowchart\mermaid-diagram-2024-09-06-075309.png">

## 6. Kesimpulan
Token Bucket adalah pilihan terbaik untuk aplikasi yang menghadapi lonjakan mendadak karena kemampuannya untuk menangani burst traffic dengan memanfaatkan token yang tersedia.
Leaky Bucket adalah pilihan yang baik untuk menjaga aliran permintaan yang stabil dan menghindari lonjakan mendadak.
Fixed Window cocok untuk aplikasi dengan pola lalu lintas yang cukup stabil, meskipun dapat menyebabkan lonjakan tajam.
Sliding Window adalah yang paling dinamis dan granular, ideal untuk aplikasi dengan pola lalu lintas yang sangat fluktuatif, tetapi memerlukan pengelolaan yang lebih kompleks.

[Sumber Literatur 1](https://blog.compliiant.io/api-defense-with-rate-limiting-using-fastapi-and-token-buckets-0f5206fc5029)
[Sumber Literatur 2 (Studi Kasus)](https://redis.io/glossary/rate-limiting/)

               
