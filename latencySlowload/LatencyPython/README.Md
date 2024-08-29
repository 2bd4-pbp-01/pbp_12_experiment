# Perbandingan Java dan Python dalam Menyelesaikan Masalah Latency dengan Asynchronous Programming

## 1. Identifikasi Masalah
Pada aplikasi transaksi yang memerlukan validasi kartu kredit, pengecekan saldo, dan penyelesaian transaksi, latency dapat menjadi masalah signifikan. Latency ini terjadi karena setiap operasi I/O (misalnya, panggilan API atau akses database) memakan waktu, yang bisa mengakibatkan penundaan dalam pemrosesan transaksi secara keseluruhan.

## 2. Deskripsi Masalah
- **Java Implementation**: Menggunakan `CompletableFuture` untuk menjalankan operasi I/O secara asinkron dalam thread pool, memungkinkan operasi lain berjalan secara paralel tanpa menunggu penyelesaian operasi sebelumnya.
- **Python Implementation**: Menggunakan `asyncio` untuk mengelola operasi I/O secara asinkron, yang memungkinkan tugas lain diproses sambil menunggu penyelesaian operasi I/O.

### Pertanyaan Utama:
Bagaimana perbedaan dalam mengatasi latency antara implementasi asynchronous programming di Java dan Python?

## 3. Metodologi Eksperimen
Eksperimen dilakukan dengan dua pendekatan berbeda:
- **Java**: Menggunakan `CompletableFuture` dengan thread pool untuk menjalankan operasi asinkron.
- **Python**: Menggunakan `asyncio` untuk menjalankan operasi asinkron dengan event loop.

### Pengukuran:
- Waktu total yang diperlukan untuk memproses transaksi.
- Efisiensi penggunaan CPU dan memori selama pemrosesan transaksi.
- Responsivitas sistem selama pemrosesan beberapa transaksi secara simultan.

## 4. Pelaksanaan Eksperimen
### Java:
- **Langkah 1**: Memulai proses validasi kartu kredit secara asinkron.
- **Langkah 2**: Memulai pengecekan saldo secara asinkron.
- **Langkah 3**: Menyelesaikan transaksi hanya setelah validasi dan pengecekan saldo selesai.
- **Langkah 4**: Mengukur waktu pemrosesan dan penggunaan sumber daya sistem.

### Python:
- **Langkah 1**: Memulai proses validasi kartu kredit menggunakan `asyncio`.
- **Langkah 2**: Melakukan pengecekan saldo menggunakan `asyncio`.
- **Langkah 3**: Menyelesaikan transaksi setelah kedua operasi di atas selesai.
- **Langkah 4**: Mengukur waktu pemrosesan dan penggunaan sumber daya sistem.

## 5. Analisis Hasil Eksperimen
### Java:
- **Waktu Pemrosesan Total**: Dipercepat karena operasi I/O berjalan paralel dalam thread pool.
- **Efisiensi Penggunaan Sumber Daya**: Menggunakan thread pool dengan `CompletableFuture` meningkatkan efisiensi dengan cara mengoptimalkan penggunaan CPU.
- **Responsivitas Sistem**: Cukup responsif, tetapi bisa menurun jika jumlah thread yang berjalan secara paralel terlalu banyak.

### Python:
- **Waktu Pemrosesan Total**: Dipercepat melalui event loop yang menangani operasi I/O secara efisien.
- **Efisiensi Penggunaan Sumber Daya**: Penggunaan `asyncio` sangat efisien, terutama dalam situasi I/O-bound, di mana operasi menunggu respons dari API atau database.
- **Responsivitas Sistem**: Sangat responsif, terutama dalam menangani tugas-tugas I/O secara simultan.

### Kesimpulan:
- **Java**: Cocok untuk aplikasi yang memerlukan pemrosesan paralel dalam skala besar, terutama yang bersifat CPU-bound.
- **Python**: Lebih unggul dalam menangani operasi I/O-bound dengan cara yang efisien dan responsif, terutama dalam skenario di mana banyak tugas I/O harus dijalankan secara simultan.

Pemilihan antara Java dan Python harus disesuaikan dengan kebutuhan spesifik aplikasi dan jenis operasi yang paling sering dijalankan.
