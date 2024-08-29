# Mengatasi Masalah Latensi pada Query Database dengan CompletableFuture di Java

## Identifikasi Problem
Pada aplikasi yang melibatkan transaksi database secara real-time, latensi yang tinggi bisa menjadi masalah signifikan. Latensi ini terjadi ketika aplikasi melakukan query secara berurutan (synchronous), yang mengakibatkan waktu eksekusi total menjadi lebih lama.

## Deskripsi Problem
Dalam sistem yang melakukan banyak query ke database, setiap query yang dijalankan secara berurutan membutuhkan waktu tertentu untuk menyelesaikan prosesnya. Pada kasus sinkron, query-query ini dijalankan satu per satu sehingga jika satu query membutuhkan waktu 2 detik, tiga query akan memakan waktu 6 detik. Masalah ini dapat menyebabkan penurunan kinerja aplikasi secara keseluruhan, terutama saat jumlah query bertambah atau saat latensi jaringan meningkat.

## Metodologi Eksperimen
Untuk mengatasi masalah ini, eksperimen dilakukan dengan membandingkan dua pendekatan:
1. **Pendekatan Sinkron (Synchronous Query):** Query dijalankan secara berurutan, satu per satu.
2. **Pendekatan Asinkron dengan CompletableFuture (Asynchronous Query):** Query dijalankan secara paralel menggunakan `CompletableFuture`, yang memungkinkan beberapa query berjalan bersamaan sehingga mengurangi waktu total eksekusi.

Langkah-langkah eksperimen adalah sebagai berikut:
- **Setup:** Persiapkan database SQLite dengan data transaksi.
- **Implementasi:** Dua versi program dibuat, satu dengan pendekatan sinkron dan satu lagi dengan pendekatan asinkron menggunakan `CompletableFuture`.
- **Pengukuran:** Waktu eksekusi total untuk kedua pendekatan diukur dan dibandingkan.

## Pelaksanaan Eksperimen
Eksperimen dilakukan dengan menjalankan kedua versi program pada data transaksi yang sama. Pada versi sinkron, setiap query dijalankan satu per satu. Pada versi asinkron, query dijalankan secara paralel menggunakan `CompletableFuture`.

1. **Versi Sinkron:**
    - Waktu total dihitung setelah semua query selesai dijalankan secara berurutan.

2. **Versi Asinkron:**
    - Waktu total dihitung setelah semua query selesai dijalankan secara paralel menggunakan `CompletableFuture.allOf`.

## Analisis Hasil Eksperimen
Hasil eksperimen menunjukkan bahwa:

- **Pendekatan Sinkron:** Waktu eksekusi total bertambah seiring dengan jumlah query yang dijalankan, karena query dilakukan secara berurutan.
- **Pendekatan Asinkron:** Waktu eksekusi total berkurang secara signifikan karena beberapa query dijalankan secara paralel, memungkinkan pemanfaatan sumber daya CPU yang lebih efisien.

Dalam kasus eksperimen ini, waktu eksekusi dengan pendekatan asinkron lebih cepat dibandingkan dengan pendekatan sinkron, menunjukkan bahwa penggunaan `CompletableFuture` efektif dalam mengurangi latensi pada aplikasi yang memerlukan query database secara intensif.
