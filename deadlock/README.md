# LAPORAN EKSPERIMEN MASALAH DEADLOCK

## Pengantar: Masalah Deadlock

**Deadlock** adalah situasi di mana dua atau lebih proses atau thread tidak dapat melanjutkan eksekusinya karena saling menunggu satu sama lain untuk melepaskan sumber daya yang sedang digunakan. Dalam konteks sistem operasi atau pemrograman multi-threaded, deadlock menjadi masalah serius karena dapat menyebabkan sistem atau aplikasi menjadi tidak responsif.

### Empat Kondisi Terjadinya Deadlock

Deadlock biasanya terjadi ketika empat kondisi berikut terpenuhi secara simultan:

1. **Mutual Exclusion (Eksklusi Saling)**:
   Setidaknya ada satu sumber daya yang tidak dapat dibagi, dan hanya satu proses yang dapat menggunakannya pada satu waktu.

2. **Hold and Wait (Menahan dan Menunggu)**:
   Proses yang sudah memiliki satu sumber daya tetap menahannya sambil menunggu sumber daya lain yang sedang dipegang oleh proses lain.

3. **No Preemption (Tanpa Pemaksaan)**:
   Sumber daya yang dimiliki oleh suatu proses tidak dapat dipaksa untuk dilepaskan hingga proses tersebut secara sukarela melepasnya.

4. **Circular Wait (Menunggu Secara Sirkular)**:
   Terdapat serangkaian proses {P1, P2, ..., Pn} di mana P1 menunggu sumber daya yang dipegang oleh P2, P2 menunggu sumber daya yang dipegang oleh P3, dan seterusnya hingga Pn menunggu sumber daya yang dipegang oleh P1, membentuk lingkaran menunggu.

### Dampak Deadlock

Jika deadlock terjadi, aplikasi atau sistem bisa berhenti bekerja, dan seringkali diperlukan intervensi manual untuk mengatasi situasi tersebut. Deadlock dapat sangat merugikan dalam sistem yang harus berjalan terus-menerus (real-time systems) atau yang mengelola data kritis.

### Strategi Penyelesaian Deadlock

Untuk menghindari atau mengatasi deadlock, terdapat beberapa strategi yang dapat diterapkan, antara lain:

- **Deadlock Prevention (Pencegahan Deadlock)**: Menghilangkan salah satu dari empat kondisi yang menyebabkan deadlock.
- **Deadlock Avoidance (Penghindaran Deadlock)**: Menggunakan informasi tambahan tentang sumber daya dan proses untuk menghindari keadaan deadlock.
- **Deadlock Detection and Recovery (Deteksi dan Pemulihan Deadlock)**: Mengizinkan deadlock terjadi, tetapi mendeteksi dan memulihkannya setelah terjadi.

Dalam eksperimen ini, fokus utama adalah pada teknik pencegahan deadlock menggunakan konsep **orderly locking**, yaitu strategi yang memastikan penguncian sumber daya dilakukan dalam urutan yang konsisten untuk menghindari deadlock.

---

## Eksperimen 1: Penyelesaian Masalah Deadlock menggunakan Bahasa Java (Ahmad)

### Eksperimen dan Analisis
1. **Menjalankan Program**
   - **Langkah-langkah**:
     Kode di atas dijalankan dalam lingkungan Java. Program ini melibatkan dua thread yang mencoba mengunci dua sumber daya (ResourceA dan ResourceB). Kedua thread (Thread 1 dan Thread 2) mengunci ResourceA terlebih dahulu, kemudian mencoba mengunci ResourceB.
   - **Output yang Diharapkan**:
     - Thread 1: Locked ResourceA
     - Thread 1: Locked ResourceB
     - Thread 2: Locked ResourceA
     - Thread 2: Locked ResourceB

2. **Analisis Hasil**
   - **Urutan Eksekusi**:
     Thread 1 berhasil mengunci ResourceA terlebih dahulu, kemudian mengunci ResourceB. Setelah itu, Thread 2 melakukan hal yang sama.
   - **Tidak Ada Deadlock**:
     Kode ini menghindari deadlock dengan memastikan bahwa kedua thread mengunci sumber daya dalam urutan yang sama. Tidak ada situasi di mana Thread 1 mengunci ResourceA dan menunggu ResourceB yang dikunci oleh Thread 2, sementara Thread 2 menunggu ResourceA yang dikunci oleh Thread 1.

3. **Kesimpulan dari Eksperimen**
   - **Deadlock Prevention**:
     Dengan mengunci sumber daya dalam urutan yang konsisten, program ini berhasil menghindari deadlock. Ini adalah contoh implementasi sederhana dari teknik "orderly locking" untuk mencegah deadlock dalam aplikasi multi-threaded.
   - **Studi Kasus Sukses**:
     Studi kasus ini menunjukkan bahwa dengan sedikit modifikasi dalam urutan penguncian, kita dapat efektif mencegah deadlock dan memastikan bahwa semua thread dapat menyelesaikan tugas mereka tanpa saling menunggu.

---

## Urutan Proses

### Urutan Proses Masalah Deadlock
Deadlock terjadi ketika dua atau lebih proses/thread saling menunggu satu sama lain untuk melepaskan sumber daya.

<p align="center">
  <img src="flowchart/Deadlock Problem.png" alt="Flowchart Masalah Deadlock">
</p>

### Solusi: Orderly Locking

**Orderly locking** adalah salah satu strategi yang digunakan untuk menghindari deadlock. Strategi ini memastikan bahwa semua proses atau thread yang ingin mengakses beberapa sumber daya bersama harus mengambil kunci sumber daya tersebut dalam urutan yang sama. Dengan cara ini, kita dapat mencegah siklus tunggu yang dapat menyebabkan deadlock.

#### Manfaat Orderly Locking

1. **Menghindari Deadlock**: Dengan urutan penguncian yang konsisten, siklus menunggu antara proses dapat dihindari.
2. **Konsistensi Akses Sumber Daya**: Semua proses mengikuti aturan yang sama saat mengakses sumber daya, yang juga membantu mencegah race condition.
3. **Mudah Diimplementasikan**: Orderly locking adalah teknik yang relatif mudah diimplementasikan dalam sistem multi-threading atau multiproses.

Namun, **orderly locking** juga memiliki beberapa keterbatasan. Salah satu kekurangannya adalah dapat meningkatkan waktu tunggu proses karena proses harus menunggu giliran untuk mendapatkan sumber daya dalam urutan tertentu. Meskipun begitu, strategi ini sangat efektif dalam lingkungan di mana deadlock menjadi ancaman nyata bagi performa sistem.

### Kapan Kondisi yang Tepat untuk Menggunakan Orderly Locking

Orderly locking adalah salah satu strategi untuk menghindari deadlock. Berikut adalah kondisi-kondisi yang tepat untuk menggunakan orderly locking:

1. **Sumber Daya yang Dibagi Antar Proses**:
   Ketika beberapa proses atau thread mengakses sumber daya bersama secara bersamaan, maka perlu ada mekanisme penguncian. Jika proses mengambil beberapa kunci, ada potensi deadlock jika tidak ada aturan yang jelas tentang urutan mengambil kunci.

2. **Multiple Locks atau Sumber Daya Dibutuhkan**:
   Jika proses memerlukan lebih dari satu sumber daya, peluang deadlock meningkat, terutama ketika proses mengakses sumber daya dalam urutan yang berbeda-beda.

3. **Potensi Cyclic Waiting**:
   Deadlock sering kali muncul dari situasi di mana proses menunggu kunci yang dipegang oleh proses lain dalam urutan yang tidak teratur, menciptakan siklus menunggu.

4. **Sumber Daya Tidak Bisa Dibagi**:
   Orderly locking bekerja dengan baik ketika sumber daya bersifat eksklusif dan harus diperoleh dalam urutan tertentu untuk menghindari kondisi race condition atau inkonsistensi data.

### Contoh Detil Kondisi Orderly Locking

Misalnya, dua proses membutuhkan File A dan File B. Kedua file ini tidak bisa diakses oleh dua proses sekaligus.

- **Kondisi tanpa Orderly Locking (Potensi Deadlock)**:
  1. Proses 1 mengunci File A.
  2. Proses 2 mengunci File B.
  3. Proses 1 mencoba mengunci File B (terkunci oleh Proses 2).
  4. Proses 2 mencoba mengunci File A (terkunci oleh Proses 1).

  Ini menyebabkan deadlock karena kedua proses saling menunggu.

- **Dengan Orderly Locking (Menghindari Deadlock)**:
  1. Proses 1 mengunci ResourceA.
  2. Proses 1 mengunci ResourceB.
  3. Proses 2 menunggu ResourceA (karena sedang dikunci oleh Proses 1).
  4. Setelah Proses 1 selesai, Proses 2 mendapatkan ResourceA dan ResourceB secara berurutan.
  
  Dengan mengikuti urutan penguncian yang konsisten (mengunci ResourceA dulu baru ResourceB), deadlock berhasil dihindari karena tidak ada siklus tunggu.

- **Kondisi dengan Orderly Locking (Menghindari Deadlock)**:
  Kita tentukan urutan penguncian sumber daya, misalnya File A harus dikunci sebelum File B. Dengan urutan ini, kedua proses mengikuti urutan yang sama dan deadlock bisa dihindari.

Berikut adalah urutan proses penyelesaian masalah Deadlock dengan solusi Orderly Locking

<p align="center">
  <img src="flowchart/Deadlock Problem.png" alt="Flowchart Masalah Deadlock">
</p>

---

## Eksperimen 2: Perbandingan Program Deadlock Prevention dalam Bahasa Java dengan Python (Ahmad)

Perbandingan antara program Deadlock Prevention dalam bahasa Java dan Python dapat dilihat dari beberapa aspek:

1. **Pengelolaan Thread**
   - **Java**: Menggunakan `Thread` untuk mengelola thread secara eksplisit.
   - **Python**: Menggunakan modul `threading` dengan pendekatan lebih sederhana.

2. **Mekanisme Penguncian (Locks)**
   - **Java**: Menggunakan `synchronized` atau `Lock` dari `java.util.concurrent.locks`.
   - **Python**: Menggunakan `Lock` dari modul `threading` dengan konteks manajer (`with`).

3. **Sinkronisasi dan Penanganan Deadlock**
   - **Java**: Menggunakan `synchronized` atau `Lock` dengan fitur seperti `tryLock`.
   - **Python**: Menggunakan `threading.Lock`, tetapi tidak memiliki fitur deteksi deadlock bawaan.

4. **Gaya Pemrograman**
   - **Java**: Menggunakan pendekatan berorientasi objek dengan sintaks yang lebih verbose.
   - **Python**: Lebih ringkas dan langsung dengan pendekatan `with` untuk penguncian.

5. **Kinerja**
   - **Java**: Kinerja threading lebih baik karena JVM yang dioptimalkan.
   - **Python**: Terbatas oleh Global Interpreter Lock (GIL), yang dapat menghambat kinerja threading.

### Kesimpulan

- **Java**: Lebih kuat dan fleksibel dalam manajemen thread, dengan kontrol yang lebih besar.
- **Python**: Lebih sederhana dan mudah digunakan, tetapi GIL bisa menjadi penghambat dalam skenario multi-threading.

