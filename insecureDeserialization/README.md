Berikut adalah versi laporan dengan istilah-istilah programming dikembalikan ke bahasa aslinya:

---

## Definisi Masalah dan Hubungannya dengan Tema Transaksi

**Insecure Deserialization** adalah kerentanan keamanan yang terjadi ketika data yang diserialisasi (encoded menjadi format yang dapat disimpan atau ditransmisikan) didekodekan kembali (deserialized) tanpa validasi atau kontrol yang memadai. Hal ini memungkinkan penyerang untuk menyisipkan data yang berbahaya ke dalam proses deserialization, yang kemudian dapat dieksekusi sebagai kode berbahaya.

Dalam konteks transaksi, terutama transaksi digital atau komunikasi data, deserialization digunakan untuk mentransfer objek antara sistem yang berbeda. Jika proses deserialization ini tidak aman, penyerang dapat mengubah data transaksi untuk mengeksekusi perintah berbahaya, mengakses data sensitif, atau memanipulasi transaksi itu sendiri, yang dapat menyebabkan kehilangan data, pencurian, atau kompromi integritas transaksi.

---

## Alasan Masalah Terjadi dan Kenapa Masalah Ini Penting

**Alasan Masalah Terjadi:**
- **Kurangnya Validasi:** Saat data yang telah diserialisasi di-deserialize kembali tanpa validasi, data tersebut dapat dieksploitasi. Misalnya, metode `__reduce__` dalam Python memungkinkan penyerang untuk menentukan cara sebuah objek di-deserialize, yang bisa memicu eksekusi kode berbahaya.
- **Reliability Terhadap Data Eksternal:** Sistem yang terlalu mempercayai data yang diterima dari luar, seperti dari pengguna atau sistem lain, tanpa verifikasi yang memadai, rentan terhadap eksploitasi.

**Pentingnya Masalah:**
- **Keamanan Transaksi:** Dalam transaksi digital, validitas dan integritas data adalah kunci. Insecure deserialization dapat digunakan untuk memanipulasi transaksi, mencuri informasi, atau melakukan tindakan berbahaya lainnya.
- **Dampak Finansial:** Exploit dari kerentanan ini bisa menyebabkan kerugian finansial besar, baik dari pencurian langsung maupun dari biaya mitigasi dan pemulihan.
- **Reliability Pengguna:** Jika pengguna atau klien merasa bahwa sistem tidak aman, kepercayaan mereka akan terganggu, yang dapat merusak reputasi organisasi atau bisnis.

---

## Cara Kerja Solusinya Per Case

> ### Pickle 
> Modul Python yang digunakan untuk _serialisasi dan deserialisasi objek_. Pickle mengubah objek Python menjadi format byte yang dapat disimpan atau dikirim, dan kemudian diubah kembali menjadi objek asli saat deserialized.

### Case 1: Kompleksitas Eksploitasi dengan `pickle`
- **Masalah:** Data yang tampaknya tidak berbahaya dapat disusupi dengan kelas berbahaya (`ExploitChain`), yang memungkinkan eksekusi perintah sistem saat deserialization.

<p align="center">
<img width="75%" img src="Diagram\Diagram Insecure Deserialization Case 1.png">
</p>

- **Solusi:** Mengganti penggunaan `pickle` dengan JSON. JSON tidak mendukung serialisasi objek yang kompleks dan tidak memungkinkan eksekusi kode, membuatnya lebih aman untuk transaksi yang melibatkan deserialization.

<p align="center">
<img width="50%" img src="Diagram\Diagram Insecure Deserialization Solution 1.png">
</p>

### Case 2: Insecure Deserialization dengan `pickle`
- **Masalah:** Kelas berbahaya (`MaliciousClass`) dapat dieksekusi saat deserialization dengan `pickle`, menjalankan perintah yang tidak diinginkan.

<p align="center">
<img width="75%" img src="Diagram\Diagram Insecure Deserialization Case 2.png">
</p>

- **Solusi:** Menggunakan `SafeUnpickler` untuk membatasi deserialization hanya pada kelas-kelas tertentu yang diizinkan. Dengan `SafeUnpickler`, hanya kelas yang terdaftar secara eksplisit yang dapat di-deserialize, mencegah kelas berbahaya untuk dieksekusi.

<p align="center">
<img width="75%" img src="Diagram\Diagram Insecure Deserialization Solution 2.png">
</p>

### Case 3: Deserialization dengan `SafeUnpickler` untuk Kompleksitas Eksploitasi
- **Masalah:** Penyerang dapat membuat eksploitasi yang kompleks menggunakan metode `__reduce__` untuk menyisipkan perintah berbahaya yang dieksekusi selama deserialization.

<p align="center">
<img width="75%" img src="Diagram\Diagram Insecure Deserialization Case 3.png">
</p>

- **Solusi:** Sama seperti solusi di Case 2, `SafeUnpickler` digunakan untuk memastikan bahwa hanya kelas tertentu yang dapat di-deserialize. Ini mencegah eksekusi kode berbahaya, bahkan jika eksploitasi yang kompleks digunakan.

<p align="center">
<img width="75%" img src="Diagram\Diagram Insecure Deserialization Solution 3.png">
</p>

---
## Rasionalisasi Studi Kasus dan Solusi
### Mengapa Ketiga Masalah Ini Bisa Terjadi
1. *Insecure Deserialization dengan Pickle (Case 1):*

    Terjadi karena pickle memungkinkan eksekusi kode saat deserialisasi. Jika penyerang bisa memanipulasi data yang diserialisasi, mereka bisa menjalankan kode berbahaya di sistem target. Masalah ini muncul jika sistem menerima data dari sumber yang tidak tepercaya tanpa validasi.

2. *Kelas Berbahaya yang Dieksekusi saat Deserialization (Case 2):*

    Terjadi ketika data yang diserialisasi berisi objek yang tidak aman atau kelas berbahaya. Jika data tidak diverifikasi, deserialization akan mengeksekusi objek ini, memungkinkan penyerang menjalankan perintah tidak sah.

3. *Eksploitasi dengan Teknik __reduce__ (Case 3):*

    Terjadi karena pickle mendukung metode __reduce__, yang bisa digunakan untuk menyisipkan perintah kompleks selama deserialization. Penyerang canggih bisa menggunakan teknik ini untuk memicu eksekusi kode tanpa disadari.

### Kapan Menggunakan 3 Solusi Case
1. *Penggunaan JSON (Case 1)*

    **Kapan digunakan**: Saat hanya diperlukan serialisasi data sederhana (seperti string, angka, atau dictionary) tanpa objek Python yang kompleks. Ini cocok untuk komunikasi antar sistem atau API yang memerlukan format yang ringan dan aman.
    **Mengapa**: JSON tidak mendukung eksekusi kode atau serialisasi objek kompleks, sehingga secara default lebih aman terhadap risiko insecure deserialization.
    **Contoh**: Data yang dikirim antar aplikasi web atau layanan microservices, di mana hanya diperlukan transfer data tanpa eksekusi logika.

2. *SafeUnpickler (Case 2)*

    **Kapan digunakan**: Ketika perlu menggunakan pickle untuk serialisasi objek kompleks, tetapi ingin membatasi objek mana yang dapat di-deserialize.
    **Mengapa**: SafeUnpickler memberikan kontrol ketat dengan membatasi deserialization hanya pada kelas-kelas yang aman, mencegah eksekusi objek berbahaya.
    **Contoh**: Aplikasi yang memerlukan serialisasi objek Python yang rumit, seperti model machine learning, tetapi ingin mencegah manipulasi oleh penyerang.

3. *Penggunaan SafeUnpickler untuk Mengatasi Eksploitasi Kompleks (Case 3)*

    **Kapan digunakan**: Saat menghadapi potensi eksploitasi yang lebih rumit, di mana penyerang mungkin menggunakan metode seperti __reduce__ untuk menyisipkan kode berbahaya selama deserialization.
    **Mengapa**: SafeUnpickler mencegah eksekusi kode berbahaya meskipun ada teknik eksploitasi lanjutan seperti __reduce__. Ini memberikan perlindungan yang lebih dalam pada skenario yang sangat berisiko.
    **Contoh**: Sistem yang berhadapan dengan penyerang canggih yang mencoba mengeksploitasi deserialization melalui kode kompleks atau objek yang tidak aman.

---

## Perbandingan Solusi dengan Parameter Efisiensi dan Kemudahan Implementasi

**Solusi 1: Penggunaan JSON**
- **Efisiensi:** Menggunakan JSON lebih efisien untuk data yang sederhana dan tidak memerlukan serialisasi objek yang kompleks. JSON cepat dan ringan, serta tidak memiliki overhead untuk keamanan tambahan.
- **Kemudahan Implementasi:** Mudah diimplementasikan karena JSON adalah format standar yang didukung luas, dan sebagian besar bahasa pemrograman memiliki pustaka bawaan untuk menangani JSON.

**Solusi 2: SafeUnpickler**
- **Efisiensi:** `SafeUnpickler` efektif dalam situasi di mana `pickle` harus digunakan, tetapi dengan perlindungan tambahan. Ada sedikit overhead dalam hal kinerja karena setiap kelas harus di-whitelist secara manual.
- **Kemudahan Implementasi:** Implementasinya memerlukan pemahaman yang lebih dalam tentang proses deserialization dan klasifikasi objek yang aman. Namun, ini memberikan kontrol yang ketat terhadap apa yang bisa di-deserialize.

**Perbandingan:**
- **JSON** lebih mudah diimplementasikan dan lebih cepat untuk data yang tidak memerlukan serialisasi kompleks. Namun, ia tidak dapat menangani objek yang sangat kompleks.
- **SafeUnpickler** lebih cocok untuk situasi di mana `pickle` tidak bisa dihindari, memberikan perlindungan yang lebih kuat tetapi dengan pengorbanan sedikit efisiensi dan kemudahan penggunaan.

---

## Hasil yang Diharapkan Sebelum dan Sesudah Solusi Diimplementasikan

**Sebelum Implementasi Solusi:**
- **Risiko Keamanan Tinggi:** Data yang diserialisasi dapat dengan mudah disusupi, menyebabkan eksekusi kode berbahaya selama deserialization.
- **Potensi Eksploitasi:** Transaksi dapat dimanipulasi, mengakibatkan pencurian data, perintah sistem yang tidak sah, atau bahkan kerugian finansial.

**Sesudah Implementasi Solusi:**
- **Keamanan yang Ditingkatkan:** Deserialization menjadi lebih aman, baik melalui penggunaan JSON yang tidak mendukung eksekusi kode, atau melalui `SafeUnpickler` yang membatasi deserialization hanya untuk kelas yang aman.
- **Proteksi Terhadap Eksploitasi:** Risiko eksekusi kode berbahaya selama deserialization diminimalkan, menjaga integritas transaksi dan data.
- **Reliability yang Lebih Baik:** Dengan mengurangi risiko keamanan, kepercayaan pengguna dan klien terhadap sistem akan meningkat, menjaga reputasi dan operasi bisnis.

---