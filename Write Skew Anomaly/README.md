Tanggal Eksperimen: 28 Agustus 2024
# Laporan: Analisis dan Solusi Write Skew Anomaly dalam Konteks Transaksi

## 1. Definisi Masalah dan Hubungannya dengan Tema Transaksi

**Write Skew Anomaly** adalah jenis masalah konsistensi data yang terjadi ketika dua atau lebih transaksi paralel membuat keputusan berdasarkan kondisi data saat ini, dan masing-masing transaksi menulis data baru tanpa menyadari perubahan yang dilakukan oleh transaksi lain. Hal ini dapat menyebabkan kondisi akhir yang melanggar aturan atau kondisi yang diinginkan, meskipun setiap transaksi secara individu tampak mengikuti aturan.

Dalam konteks transaksi, terutama dalam basis data yang mendukung transaksi paralel, write skew dapat terjadi ketika dua atau lebih transaksi mengubah data dengan cara yang, jika dilihat bersama, melanggar aturan atau kondisi bisnis. Ini sering muncul dalam situasi di mana tidak ada isolasi transaksi yang memadai, yang menyebabkan inkonsistensi data.

---

## 2. Alasan Masalah Terjadi dan Kenapa Masalah Ini Penting

**Alasan Masalah Terjadi:**
- **Kurangnya Isolasi:** Ketika transaksi paralel tidak diisolasi dengan baik, mereka dapat membaca dan menulis data yang saling mempengaruhi tanpa menyadari adanya perubahan oleh transaksi lain. Hal ini sering terjadi dalam sistem yang menggunakan isolasi tingkat rendah atau tidak ada mekanisme locking yang tepat.
- **Keputusan Berdasarkan Data yang Kedaluwarsa:** Setiap transaksi membuat keputusan berdasarkan data yang mungkin telah diubah oleh transaksi lain yang berjalan secara bersamaan, mengakibatkan perubahan yang tidak konsisten atau melanggar aturan.

**Pentingnya Masalah:**
- **Konsistensi Transaksi:** Masalah ini dapat menyebabkan ketidakkonsistenan data, yang dapat melanggar aturan bisnis kritis, merusak integritas data, dan menyebabkan kerugian finansial.
- **Keamanan dan Kepercayaan:** Dalam sistem keuangan atau operasional, pelanggaran aturan akibat write skew dapat merusak kepercayaan pengguna dan mengakibatkan implikasi hukum jika aturan bisnis dilanggar.

---

## 3. Cara Kerja Solusinya Per Case

### Masalah
<p align="center">
<img src="Diagram\Diagram Write Skew Case 1.png">
</p>

### Case 1: Pessimistic Locking
- **Masalah:** Dua transaksi paralel mencoba memperbarui catatan yang sama, yang dapat menyebabkan kondisi akhir yang tidak konsisten jika tidak ada isolasi.
- **Solusi:** Dengan **Pessimistic Locking**, catatan yang akan diubah oleh satu transaksi dikunci sehingga transaksi lain harus menunggu hingga transaksi pertama selesai sebelum melakukan perubahan. Ini mencegah write skew dengan memastikan hanya satu transaksi yang dapat mengubah data pada satu waktu.

<p align="center">
<img src="Diagram\Diagram Write Skew Solution 1.png">
</p>

### Case 2: Optimistic Concurrency Control (OCC)
- **Masalah:** Transaksi berjalan secara paralel tanpa mengunci catatan, dan pada akhir transaksi, perubahan dari kedua transaksi digabungkan, yang dapat menyebabkan pelanggaran aturan bisnis.
- **Solusi:** **Optimistic Concurrency Control** (OCC) mengizinkan transaksi berjalan secara paralel tanpa kunci, tetapi pada akhir transaksi, data yang digunakan diverifikasi untuk melihat apakah ada perubahan sejak transaksi dimulai. Jika ada perubahan, transaksi dibatalkan atau diulang, mencegah ketidakkonsistenan.

<p align="center">
<img src="Diagram\Diagram Write Skew Solution 2.png">
</p>

---

## 4. Perbandingan Solusi dengan Parameter Efisiensi dan Kemudahan Implementasi

**Solusi 1: Pessimistic Locking**
- **Efisiensi:** Menjamin konsistensi data yang sangat tinggi karena hanya satu transaksi yang dapat memodifikasi data pada satu waktu. Namun, dapat menyebabkan bottleneck dan deadlock.
- **Kemudahan Implementasi:** Relatif mudah diimplementasikan karena hanya memerlukan mekanisme penguncian yang sederhana, tetapi mengelola deadlock memerlukan logika tambahan.

**Solusi 2: Optimistic Concurrency Control (OCC)**
- **Efisiensi:** Memberikan kinerja yang lebih baik dalam sistem dengan volume transaksi tinggi dan konflik yang jarang terjadi, karena tidak ada kunci yang diambil.
- **Kemudahan Implementasi:** Tidak ada risiko deadlock, tetapi memerlukan logika tambahan untuk mendeteksi dan menangani konflik, yang menambah kompleksitas.

**Perbandingan:**
- **Pessimistic Locking** lebih cocok untuk sistem di mana konsistensi sangat kritis dan transaksi yang bersaing sering terjadi, tetapi dengan pengorbanan pada kinerja.
- **Optimistic Concurrency Control** lebih baik untuk sistem dengan volume transaksi tinggi dan konflik jarang, karena lebih efisien dan skalabel, meskipun lebih rumit untuk diimplementasikan.

---

## 5. Hasil yang Diharapkan Sebelum dan Sesudah Solusi Diimplementasikan

**Sebelum Implementasi Solusi:**
- **Risiko Ketidakkonsistenan Data:** Data yang sama dapat diubah oleh beberapa transaksi secara paralel, menyebabkan ketidakkonsistenan dan pelanggaran aturan bisnis.
- **Potensi Pelanggaran Aturan Bisnis:** Transaksi dapat menghasilkan kondisi akhir yang melanggar aturan yang ditentukan, mengakibatkan kerugian finansial atau operasional.

**Sesudah Implementasi Solusi:**
- **Konsistensi Data yang Terjamin:** Dengan **Pessimistic Locking**, data akan selalu konsisten karena hanya satu transaksi yang dapat memodifikasi data pada satu waktu. Dengan **OCC**, meskipun lebih efisien, data tetap konsisten melalui deteksi dan penanganan konflik.
- **Proteksi Terhadap Ketidakkonsistenan:** Risiko write skew diminimalkan, menjaga integritas dan keandalan sistem transaksi.
- **Kepercayaan yang Lebih Baik:** Dengan konsistensi data yang terjaga, kepercayaan pengguna terhadap sistem meningkat, mengurangi risiko kerugian finansial dan meningkatkan reputasi bisnis.