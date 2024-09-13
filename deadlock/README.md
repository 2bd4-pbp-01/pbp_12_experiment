# LAPORAN 12 EKSPERIMEN KELOMPOK 1

## Eksperimen 1 : Penyelesaian Masalah Deadlock menggunakan Bahasa Java (AHMAD)
Eksperimen dan Analisis

1. Menjalankan Program
- Langkah-langkah
 
 Kode di atas dijalankan dalam lingkungan Java. Program ini melibatkan dua thread yang mencoba mengunci dua sumber daya (ResourceA dan ResourceB).
 
  Kedua thread (Thread 1 dan Thread 2) mengunci ResourceA terlebih dahulu, kemudian mencoba mengunci ResourceB.
  
Output yang Diharapkan:

Thread 1: Locked ResourceA

Thread 1: Locked ResourceB

Thread 2: Locked ResourceA

Thread 2: Locked ResourceB

2. Analisis Hasil
- Urutan Eksekusi

 Thread 1 berhasil mengunci ResourceA terlebih dahulu, kemudian berhasil mengunci ResourceB. Setelah itu, Thread 2 melakukan hal yang sama.
 
- Tidak Ada Deadlock:

Kode ini menghindari deadlock dengan memastikan bahwa kedua thread mengunci sumber daya dalam urutan yang sama. Tidak ada deadlock karena tidak ada situasi di mana Thread 1 mengunci ResourceA dan menunggu ResourceB yang dikunci oleh Thread 2, sementara Thread 2 menunggu ResourceA yang dikunci oleh Thread 1.

3. Kesimpulan dari Eksperimen
- Deadlock Prevention:

Dengan mengunci sumber daya dalam urutan yang konsisten, program ini berhasil menghindari deadlock. Ini adalah contoh implementasi sederhana dari teknik orderly locking untuk mencegah deadlock dalam aplikasi multi-threaded.

- Studi Kasus Sukses:

Studi kasus ini menunjukkan bahwa dengan sedikit modifikasi dalam urutan penguncian, kita dapat dengan efektif mencegah deadlock dan memastikan bahwa semua thread dapat menyelesaikan tugas mereka tanpa saling menunggu.

Eksperimen ini menunjukkan bagaimana penguncian sumber daya dalam urutan yang konsisten merupakan strategi yang efektif untuk menghindari deadlock dalam aplikasi yang menggunakan threading di Java.

---

## Urutan Proses

### Urutan Proses Masalah Deadlock
- Masalah deadlock, dimana 2 atau lebih proses/thread saling menunggu satu sama lain untuk melepaskan resource

 <p align="center">
 <img src="flowchart\Deadlock Problem.png">
 </p>

## Kapan Kondisi yang tepat untuk menggunakan Orderly Locking

Orderly locking adalah salah satu strategi untuk menghindari deadlock. Deadlock adalah situasi di mana dua atau lebih proses menunggu satu sama lain untuk melepaskan sumber daya, sehingga tidak ada proses yang bisa melanjutkan eksekusi. Strategi orderly locking (penguncian terurut) berfungsi dengan menentukan urutan tetap (atau aturan) untuk memperoleh kunci atau sumber daya sehingga mencegah deadlock terjadi.
Kondisi yang Tepat untuk Menggunakan Orderly Locking:

   - Terdapat Sumber Daya yang Dibagi Antar Proses: Ketika beberapa proses atau thread mengakses beberapa sumber daya bersama secara bersamaan (misalnya, variabel, file, atau perangkat keras), maka perlu ada mekanisme untuk mengunci sumber daya tersebut. Jika proses mengambil beberapa kunci, ada potensi deadlock jika tidak ada aturan yang jelas tentang urutan mengambil kunci.

  -  Multiple Locks atau Sumber Daya yang Dibutuhkan: Jika setiap proses hanya membutuhkan satu sumber daya, deadlock biasanya tidak terjadi. Namun, jika sebuah proses memerlukan lebih dari satu sumber daya sekaligus, peluang terjadinya deadlock meningkat, terutama ketika proses mencoba mengakses sumber daya dalam urutan yang berbeda-beda.

   - Ketika Ada Potensi untuk Cyclic Waiting: Deadlock sering kali muncul dari situasi di mana proses menunggu kunci yang dipegang oleh proses lain dalam urutan yang tidak teratur, menciptakan lingkaran menunggu yang tidak bisa diselesaikan tanpa mengganggu proses.

   -  Sumber Daya Tersebut Tidak Dapat Dipecah atau Dibagi: Orderly locking bekerja dengan baik ketika sumber daya bersifat eksklusif (tidak dapat dibagi), dan harus diperoleh dalam urutan tertentu untuk menghindari kondisi balapan (race condition) atau inkonsistensi data.

Contoh Detil Kondisi Orderly Locking

Misalkan ada dua proses yang berjalan dalam sebuah aplikasi dan kedua proses tersebut membutuhkan dua sumber daya yang sama. Misalkan sumber daya tersebut adalah File A dan File B. Kedua file ini tidak bisa diakses oleh dua proses sekaligus, jadi proses harus mengunci file saat mereka menggunakannya.
Kondisi tanpa Orderly Locking (Potensi Deadlock)

   Proses 1 mengunci File A.
   Proses 2 mengunci File B.
   Proses 1 sekarang mencoba mengunci File B, tetapi File B sudah dikunci oleh Proses 2.
   Proses 2 mencoba mengunci File A, tetapi File A sudah dikunci oleh Proses 1.

Ini menciptakan situasi deadlock karena kedua proses menunggu satu sama lain untuk melepaskan sumber daya, dan tidak ada yang bisa melanjutkan eksekusi.
Kondisi dengan Orderly Locking (Menghindari Deadlock)

Untuk menghindari situasi deadlock di atas, kita dapat menerapkan orderly locking. Kita menentukan urutan penguncian sumber daya, misalnya urutannya adalah File A harus dikunci sebelum File B.

   Proses 1 harus selalu mengunci File A dulu, baru kemudian mengunci File B.
   Proses 2 juga harus mengikuti aturan yang sama, yaitu mengunci File A dulu sebelum mengunci File B.

Dengan aturan ini, kedua proses selalu mengikuti urutan yang sama saat mengunci sumber daya. Jadi, jika Proses 1 telah mengunci File A, Proses 2 tidak akan pernah bisa mengunci File B sebelum Proses 1 melepaskan kunci File A, sehingga menghindari deadlock.
Ringkasan

Orderly locking adalah solusi yang efektif dalam situasi di mana banyak proses mengakses beberapa sumber daya bersama yang eksklusif, dan diperlukan lebih dari satu sumber daya dalam waktu yang bersamaan. Dengan menetapkan urutan penguncian yang tetap untuk semua proses, strategi ini mencegah lingkaran tunggu yang menjadi penyebab utama deadlock.

### Urutan Proses Penyelesaian Masalah Deadlock Menggunakan Orderly Locking

### Penyelesaian menggunakan Orderly Locking,
- Orderly Locking (atau dikenal sebagai Lock Ordering) adalah salah satu teknik untuk mencegah terjadinya deadlock dalam sistem transaksi. Teknik ini mengharuskan transaksi untuk mengunci sumber daya dalam urutan tertentu yang telah ditentukan sebelumnya, sehingga menghilangkan kemungkinan terjadinya siklus tunggu yang menyebabkan deadlock.

### Konsep Orderly Locking:
- Penentuan Urutan Penguncian: Semua transaksi dalam sistem harus mengikuti urutan yang sama ketika mengunci sumber daya. Misalnya, jika ada sumber daya A, B, dan C, maka semua transaksi harus mengunci A dulu, lalu B, dan terakhir C, tanpa melompati urutan.

- Menghindari Saling Tunggu (Circular Wait): Karena semua transaksi mengikuti urutan penguncian yang sama, tidak ada transaksi yang akan menunggu sumber daya yang dipegang oleh transaksi lain dalam urutan yang berlawanan. Ini menghilangkan kondisi saling tunggu, yang merupakan salah satu syarat utama terjadinya deadlock.

- Implementasi Mudah: Solusi ini relatif mudah diterapkan dibandingkan dengan deteksi dan pemulihan deadlock, karena hanya memerlukan penyesuaian urutan penguncian sumber daya.

Berikut adalah Urutan Proses dari solusi Orderly Waiting

<p align="center">
 <img src="flowchart\Deadlock Solution.png">
 </p>

---

## Eksperimen 2 : Perbandingan antara program DeadlockPrevention dalam Bahasa Java dengan Bahasa Python (AHMAD)
Perbandingan antara program Deadlock Prevention dalam bahasa Java dan Python dapat dilihat dari beberapa aspek utama, seperti pengelolaan thread, mekanisme penguncian, penanganan sinkronisasi, dan gaya pemrograman. Berikut adalah perbandingannya:

1. Pengelolaan Thread
- Java:

Menggunakan kelas Thread untuk membuat dan mengelola thread. Pengelolaan thread di Java sangat eksplisit, dan Java menyediakan API yang kaya untuk thread management seperti Thread, Runnable, ExecutorService, dan lain-lain.

Contoh: Thread thread1 = new Thread(() -> {...}); thread1.start();

- Python:

Menggunakan modul threading untuk mengelola thread. Pendekatan di Python cenderung lebih sederhana, dengan penggunaan fungsi-fungsi dari modul threading.

Contoh: thread1 = threading.Thread(target=function_name, args=(...));
thread1.start();

- Perbandingan:

Java lebih eksplisit dalam manajemen thread dengan lebih banyak pilihan dan kontrol, sedangkan Python menawarkan pendekatan yang lebih sederhana dan langsung.

2. Mekanisme Penguncian (Locks)
- Java:

Menggunakan kata kunci synchronized atau kelas Lock dari java.util.concurrent.locks. synchronized secara otomatis mengunci objek dan membuat metode atau blok kode aman dari akses thread lainnya.

Contoh: synchronized(resource) { ... }
- Python:

Menggunakan Lock dari modul threading. Penguncian dilakukan dengan menggunakan konteks manajer (with statement), yang memudahkan untuk memastikan bahwa kunci dilepaskan setelah penggunaannya selesai.

Contoh: with lock_a: ...

- Perbandingan:

Python menggunakan pendekatan yang lebih Pythonic dengan konteks manajer (with), yang mengurangi kesalahan dalam manajemen sumber daya, sedangkan Java memiliki sintaks yang lebih eksplisit dengan synchronized atau Lock.

3. Sinkronisasi dan Penanganan Deadlock
- Java:

Java memungkinkan penggunaan synchronized pada metode atau blok kode untuk memastikan bahwa hanya satu thread yang dapat menjalankan kode tersebut pada satu waktu. Dengan menggunakan Lock dan ReentrantLock, Java juga menyediakan kontrol yang lebih granular atas penguncian, termasuk fitur seperti tryLock untuk menghindari deadlock.

Java juga mendukung fitur deadlock detection pada tingkat sistem operasi atau JVM.
- Python:

Python menggunakan threading.Lock untuk mengelola penguncian secara manual. Pendekatan dengan with lock_a lebih sederhana dan memastikan bahwa kunci dilepaskan secara otomatis.

Python tidak memiliki fitur bawaan untuk deteksi deadlock, sehingga program harus ditulis dengan hati-hati untuk menghindari deadlock.
- Perbandingan:

Java menawarkan lebih banyak fitur terkait dengan penguncian dan deadlock, termasuk mekanisme untuk mencoba dan mendeteksi masalah tersebut, sedangkan Python mengandalkan penguncian manual dengan pendekatan yang lebih sederhana dan mudah dibaca.
4. Gaya Pemrograman
- Java:

Java menggunakan pendekatan berorientasi objek yang ketat, dengan sintaks yang lebih verbose dan struktur yang eksplisit. Hal ini memberikan kontrol yang lebih besar kepada programmer, tetapi bisa memerlukan lebih banyak kode boilerplate.
Penggunaan synchronized atau Lock sering kali menghasilkan kode yang lebih panjang dan eksplisit.
- Python:

Python menggunakan gaya yang lebih ringkas dan langsung dengan pendekatan with untuk penguncian. Ini mengarah pada kode yang lebih mudah dibaca dan dipahami, tetapi dengan fleksibilitas yang lebih sedikit dibandingkan Java dalam beberapa hal.

Python's dynamic typing dan pendekatan sederhana sering kali membuat kode lebih mudah untuk ditulis dan dibaca, tetapi dengan risiko kesalahan yang lebih tinggi jika tidak dikelola dengan benar.
- Perbandingan:

Python lebih ringkas dan mudah dibaca, dengan pendekatan yang lebih langsung untuk sinkronisasi, sementara Java memberikan kontrol yang lebih besar dan eksplisit, sering kali dengan lebih banyak boilerplate code.

5. Kinerja
- Java:

Java dikenal untuk kinerja tinggi dalam pengelolaan thread dan sinkronisasi karena JVM yang dioptimalkan. Penggunaan Lock dan synchronized memberikan performa yang baik dalam lingkungan multi-threaded.
- Python:

Python memiliki Global Interpreter Lock (GIL), yang membatasi kinerja threading karena hanya satu thread yang dapat dieksekusi pada waktu tertentu dalam interpreter Python. Ini dapat menjadi bottleneck dalam aplikasi multi-threaded.
- Perbandingan:

Java cenderung lebih unggul dalam hal kinerja threading, terutama untuk aplikasi yang memerlukan pemrosesan paralel yang intensif. Python lebih sederhana, tetapi GIL dapat membatasi kinerja dalam skenario multi-threading.
6. Kesimpulan
- Java:

Lebih kuat dan fleksibel dalam manajemen thread dan penguncian, dengan kontrol yang lebih besar dan kinerja yang lebih baik di lingkungan multi-threaded. Cocok untuk aplikasi yang memerlukan pengelolaan sumber daya yang rumit.
- Python:

Lebih sederhana dan mudah digunakan, dengan kode yang lebih ringkas dan mudah dibaca. Cocok untuk aplikasi yang tidak terlalu berat dalam hal threading, atau untuk pengembangan yang cepat dengan fokus pada kemudahan penggunaan.
Pemilihan antara Java dan Python untuk manajemen deadlock tergantung pada kebutuhan spesifik aplikasi, seperti kinerja, kompleksitas, dan kemudahan penggunaan.
