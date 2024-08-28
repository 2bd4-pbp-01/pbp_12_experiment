# Penyelesaian Latency dengan CompletableFuture 

## Identifikasi Masalah

Dalam aplikasi pemrosesan transaksi, terdapat beberapa langkah yang memerlukan waktu, seperti validasi kartu kredit, pengecekan saldo, dan penyelesaian transaksi. Jika setiap langkah dilakukan secara sinkron, pemrosesan transaksi menjadi lambat karena setiap langkah harus menunggu langkah sebelumnya selesai. Hal ini menyebabkan peningkatan latensi dan menurunkan performa aplikasi.

## Deskripsi Masalah

Kode awal melakukan pemrosesan transaksi secara sinkron. Setiap langkah (validasi kartu kredit, pengecekan saldo, dan penyelesaian transaksi) harus diselesaikan satu per satu, memblokir eksekusi program hingga langkah sebelumnya selesai. Pendekatan ini mengakibatkan waktu pemrosesan yang lama dan latensi yang tinggi, karena setiap operasi harus menunggu penyelesaian operasi sebelumnya.

## Metodologi Eksperimen

1. **Tujuan Eksperimen:**
    - Mengurangi latensi dan waktu pemrosesan transaksi dengan menggunakan `CompletableFuture` untuk menjalankan beberapa tugas secara asinkron dan bersamaan.

2. **Langkah Eksperimen:**
    - **Tanpa `CompletableFuture`:** Pemrosesan dilakukan secara sinkron, dan setiap tugas menunggu tugas sebelumnya selesai.
    - **Dengan `CompletableFuture`:** Tugas dilakukan secara asinkron, memanfaatkan `CompletableFuture` untuk menjalankan validasi kartu kredit dan pengecekan saldo secara bersamaan, kemudian menyelesaikan transaksi setelah kedua tugas tersebut selesai.

## Pelaksanaan Eksperimen

1. **Implementasi Tanpa `CompletableFuture`:**
    - Pemrosesan transaksi dilakukan secara sinkron. Validasi kartu kredit, pengecekan saldo, dan penyelesaian transaksi dilakukan satu per satu. Setiap langkah memblokir eksekusi hingga selesai.

2. **Implementasi Dengan `CompletableFuture`:**
    - Gunakan `CompletableFuture` untuk menjalankan validasi kartu kredit dan pengecekan saldo secara bersamaan. Setelah kedua tugas tersebut selesai, lakukan penyelesaian transaksi. Ini memungkinkan operasi berjalan secara bersamaan, mengurangi latensi dan meningkatkan efisiensi.

## Analisis Hasil Eksperimen

- **Waktu Pemrosesan:**
  Dengan menggunakan `CompletableFuture`, waktu pemrosesan transaksi menjadi lebih cepat karena validasi kartu kredit dan pengecekan saldo berjalan bersamaan. Ini mengurangi waktu total yang diperlukan untuk menyelesaikan transaksi dibandingkan dengan metode sinkron di mana setiap langkah menunggu langkah sebelumnya selesai.

- **Efisiensi:**
  `CompletableFuture` meningkatkan efisiensi pemrosesan dengan memungkinkan tugas berjalan secara bersamaan (concurrent processing), sehingga mengoptimalkan penggunaan sumber daya dan mengurangi latensi.

- **Kepuasan Pengguna:**
  Dengan waktu pemrosesan yang lebih cepat, pengalaman pengguna meningkat karena aplikasi lebih responsif dan mampu menyelesaikan transaksi dengan lebih efisien.

## Kesimpulan

Penggunaan `CompletableFuture` secara signifikan mengurangi latensi dalam pemrosesan transaksi dengan memungkinkan eksekusi tugas secara asinkron dan bersamaan. Ini membuat aplikasi lebih responsif dan efisien, terutama dalam skenario di mana banyak operasi yang memerlukan waktu harus dilakukan secara bersamaan. Metode ini mengoptimalkan performa aplikasi dan meningkatkan pengalaman pengguna.

# Penyelesaian SlowLoad dengan Lazy Loading dan Caching

## Identifikasi Masalah

Dalam aplikasi yang memproses transaksi, pengambilan data yang besar dari database dapat menyebabkan masalah performa. Dalam contoh ini, masalah utamanya adalah "slow load" ketika memuat data transaksi yang besar. Masalah ini mempengaruhi waktu respon aplikasi dan pengalaman pengguna.

## Deskripsi Masalah

Aplikasi mengalami keterlambatan yang signifikan ketika memuat data transaksi dari database. Ini terjadi karena seluruh data transaksi diambil sekaligus dari database, yang menyebabkan waktu muat yang lama dan mengakibatkan aplikasi terasa lambat dan tidak responsif. Pendekatan ini tidak efisien, terutama jika data yang dimuat sangat besar.

## Metodologi Eksperimen

Untuk mengatasi masalah slow load, dua teknik digunakan:

1. **Lazy Loading**: Teknik ini memuat data hanya ketika diperlukan, bukan saat aplikasi dimulai. Ini dapat mengurangi waktu mulai aplikasi dan menghindari pemuatan data yang tidak diperlukan pada awalnya.

2. **Caching**: Teknik ini menyimpan data yang sudah dimuat dalam memori sehingga tidak perlu diambil dari database setiap kali. Ini mengurangi beban pada database dan mempercepat akses data yang sering dibutuhkan.

### Langkah-langkah Eksperimen

1. **Implementasi Awal**: Implementasikan pengambilan data tanpa menggunakan teknik lazy loading atau caching. Observasi waktu yang dibutuhkan untuk memuat data dan dampaknya terhadap kinerja aplikasi.

2. **Implementasi Lazy Loading**: Modifikasi aplikasi untuk menggunakan teknik lazy loading. Data transaksi hanya diambil dari database saat pertama kali dibutuhkan, dan disimpan dalam memori untuk akses di masa depan.

3. **Implementasi Caching**: Setelah menerapkan lazy loading, tambahkan mekanisme caching untuk menyimpan data yang sering diakses. Ini memastikan bahwa data yang sudah dimuat tidak perlu diambil ulang dari database.

## Pelaksanaan Eksperimen

1. **Pengujian Tanpa Lazy Loading dan Caching**:
    - Jalankan aplikasi dan amati waktu yang dibutuhkan untuk memuat data transaksi.
    - Catat waktu mulai dan selesai pemuatan data.

2. **Pengujian dengan Lazy Loading**:
    - Modifikasi kode untuk menerapkan lazy loading.
    - Jalankan aplikasi dan amati apakah waktu pemuatan data saat pertama kali dibutuhkan menjadi lebih efisien.
    - Verifikasi bahwa data diambil dari database hanya saat diperlukan.

3. **Pengujian dengan Caching**:
    - Tambahkan mekanisme caching pada aplikasi yang sudah menerapkan lazy loading.
    - Jalankan aplikasi dan amati perbedaan waktu akses data yang telah dimuat sebelumnya dibandingkan dengan data yang belum dimuat.
    - Verifikasi bahwa data yang telah dimuat disimpan dengan benar dan diakses lebih cepat pada permintaan berikutnya.

## Analisis Hasil Eksperimen

1. **Tanpa Lazy Loading dan Caching**:
    - Observasi waktu muat yang panjang dan dampaknya terhadap performa aplikasi.
    - Data transaksi mempengaruhi waktu respons aplikasi secara signifikan.

2. **Dengan Lazy Loading**:
    - Waktu muat awal aplikasi menjadi lebih cepat karena data tidak diambil sekaligus.
    - Data hanya dimuat saat diperlukan, mengurangi beban awal aplikasi.

3. **Dengan Caching**:
    - Akses data yang telah dimuat menjadi jauh lebih cepat.
    - Mengurangi jumlah panggilan ke database dan meningkatkan performa aplikasi secara keseluruhan.

Dengan menerapkan teknik lazy loading dan caching, aplikasi mengalami perbaikan signifikan dalam waktu akses data dan performa, mengurangi latensi, dan meningkatkan pengalaman pengguna.

