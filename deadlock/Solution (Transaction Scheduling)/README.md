# Transaction Scheduling untuk Mencegah Deadlock

Transaction Scheduling adalah salah satu metode untuk mencegah atau mengelola deadlock dalam sistem basis data atau sistem multi-threading. Dengan mengatur urutan eksekusi transaksi berdasarkan aturan tertentu, metode ini bertujuan untuk mencegah kondisi di mana transaksi saling terkunci, yang menyebabkan deadlock.

## Konsep Transaction Scheduling

Transaction scheduling melibatkan pengaturan urutan atau waktu eksekusi transaksi agar deadlock bisa dihindari. Dua pendekatan umum dalam transaction scheduling yang terkait dengan deadlock adalah:

### 1. Wait-Die Scheme (Skema Tunggu-Mati)

**Prinsip**: Setiap transaksi yang meminta sumber daya memiliki prioritas, yang biasanya ditentukan oleh waktu mulainya (timestamp).

**Aturan**:
- Jika transaksi dengan prioritas lebih tinggi (lebih tua) ingin mengakses sumber daya yang dipegang oleh transaksi prioritas lebih rendah (lebih muda), transaksi dengan prioritas tinggi menunggu hingga transaksi lebih rendah melepaskan sumber daya.
- Jika transaksi dengan prioritas lebih rendah mencoba mengakses sumber daya yang dipegang oleh transaksi dengan prioritas lebih tinggi, transaksi dengan prioritas rendah akan mati (die) dan harus dibatalkan serta di-restart di kemudian waktu.

**Manfaat**: Skema ini mencegah transaksi muda menunggu transaksi tua, yang dapat menyebabkan siklus menunggu, sehingga mencegah deadlock.

### 2. Wound-Wait Scheme (Skema Luka-Tunggu)

**Prinsip**: Sama dengan Wait-Die, setiap transaksi memiliki prioritas.

**Aturan**:
- Jika transaksi dengan prioritas lebih tinggi (lebih tua) meminta sumber daya yang sedang digunakan oleh transaksi dengan prioritas lebih rendah, transaksi yang lebih tua melukai (wound) transaksi yang lebih muda dengan memaksanya untuk dibatalkan (rollback). Transaksi yang lebih tua langsung mendapatkan sumber daya.
- Jika transaksi yang lebih muda meminta sumber daya yang dipegang oleh transaksi yang lebih tua, transaksi yang lebih muda harus menunggu.

**Manfaat**: Skema ini juga mencegah deadlock dengan meminimalkan transaksi yang menunggu dan memaksa transaksi muda untuk melakukan rollback jika perlu.

## Kapan Solusi Transaction Scheduling Harus Digunakan

1. **Saat Sistem Sering Mengalami Deadlock**:
   Jika dalam sistem basis data terdapat transaksi yang sering mengalami deadlock, salah satu skema di atas dapat diterapkan. Misalnya, pada sistem yang sangat padat transaksi (high concurrency), deadlock menjadi masalah yang sering muncul ketika transaksi berebut sumber daya yang sama.

2. **Sistem yang Memiliki Transaksi dengan Prioritas Berbeda**:
   Dalam sistem yang memiliki transaksi dengan prioritas berbeda, seperti transaksi real-time atau prioritas tinggi (misalnya transaksi keuangan penting yang tidak boleh tertunda), skema seperti Wound-Wait sangat berguna. Dengan skema ini, transaksi prioritas lebih tinggi dapat segera memperoleh sumber daya yang dibutuhkan tanpa harus menunggu transaksi prioritas rendah.

3. **Sistem dengan Transaksi Panjang dan Pendek**:
   Skema ini efektif di lingkungan di mana ada campuran transaksi yang memakan waktu lama (long transactions) dan transaksi cepat (short transactions). Transaksi pendek dapat memperoleh sumber daya lebih cepat dengan skema yang tepat, sementara transaksi panjang bisa ditangani tanpa menyebabkan deadlock.

4. **Lingkungan yang Menghadapi Rollback yang Mahal**:
   Jika biaya rollback sangat mahal dalam hal sumber daya, waktu, atau integritas data, skema seperti Wait-Die mungkin lebih cocok. Dalam skema ini, transaksi yang lebih tua cenderung menunggu, sehingga rollback terjadi lebih jarang dan hanya untuk transaksi yang lebih muda.

## Kelebihan Transaction Scheduling untuk Mencegah Deadlock

1. **Efektifitas dalam Mencegah Deadlock**:
   Dengan menggunakan penjadwalan yang tepat, siklus menunggu yang menyebabkan deadlock dapat dihindari sepenuhnya.

2. **Kemudahan Implementasi**:
   Skema seperti Wait-Die dan Wound-Wait relatif mudah diterapkan dibandingkan dengan metode seperti deteksi dan recovery deadlock, karena skema ini hanya bergantung pada prioritas transaksi.

3. **Pengaturan Prioritas**:
   Skema ini memungkinkan penanganan transaksi prioritas tinggi lebih cepat dengan tetap mempertahankan efisiensi sistem secara keseluruhan.

## Keterbatasan Transaction Scheduling

1. **Overhead pada Restart**:
   Pada skema seperti Wound-Wait, transaksi dengan prioritas lebih rendah bisa sering dibatalkan, menyebabkan overhead terkait dengan rollback dan restart transaksi.

2. **Ketidakadilan (Starvation)**:
   Ada potensi ketidakadilan bagi transaksi dengan prioritas lebih rendah, yang bisa terus menerus dibatalkan jika transaksi prioritas tinggi sering meminta sumber daya yang sama.

3. **Kompleksitas Manajemen Prioritas**:
   Menentukan prioritas transaksi secara efektif memerlukan strategi yang matang untuk memastikan bahwa transaksi diprioritaskan dengan benar, terutama dalam sistem yang dinamis.

## Contoh Kasus Penggunaan Transaction Scheduling

1. **Sistem Perbankan**:
   Dalam transaksi perbankan, ada transaksi seperti transfer dana antar rekening yang membutuhkan prioritas lebih tinggi dibandingkan dengan transaksi seperti pembaruan profil pengguna. Dalam sistem seperti ini, Wound-Wait bisa digunakan untuk memastikan transaksi penting diproses dengan cepat.

2. **Sistem Pemesanan Tiket**:
   Dalam aplikasi pemesanan tiket, transaksi yang memerlukan konfirmasi cepat (misalnya, pembelian tiket) harus diutamakan dibandingkan dengan transaksi pencarian tiket. Skema seperti Wait-Die bisa digunakan di sini.

## Kesimpulan

Transaction scheduling adalah solusi yang berguna dalam menghindari deadlock, terutama dalam sistem dengan tingkat transaksi yang tinggi atau dengan prioritas transaksi yang berbeda-beda. Solusi ini harus digunakan saat sistem sering menghadapi deadlock, saat ada transaksi dengan prioritas berbeda, atau ketika rollback menjadi terlalu mahal.

---

# Abstraksi, OOP, dan Eksperimen Transaction Scheduling

## Apa itu Abstraksi dan OOP?

### Abstraksi

Abstraksi adalah konsep dalam pemrograman yang memungkinkan kita untuk menyembunyikan detail implementasi yang kompleks dan hanya menunjukkan fitur penting kepada pengguna. Ini memungkinkan pengembang untuk bekerja dengan representasi yang lebih sederhana dari elemen yang kompleks. Dengan abstraksi, kita dapat fokus pada apa yang dilakukan sistem, daripada bagaimana sistem itu bekerja secara detail. Dalam kode, abstraksi diterapkan melalui penggunaan fungsi, kelas, dan metode yang menyederhanakan pekerjaan pengembang.

### Object-Oriented Programming (OOP)

Object-Oriented Programming (OOP) adalah paradigma pemrograman yang menggunakan konsep objek dan kelas untuk menyusun program. Dalam OOP, kelas adalah cetak biru atau template untuk membuat objek. Setiap objek adalah instansiasi dari kelas, yang memiliki data (disebut properti atau atribut) dan perilaku (disebut metode). OOP memungkinkan kita untuk membuat hubungan antar-objek melalui prinsip-prinsip seperti inheritance (pewarisan), polymorphism, encapsulation (pembungkusan), dan abstraction (abstraksi).

## Hubungan Abstraksi dan OOP dengan Eksperimen Ini

Eksperimen ini menggunakan penjadwalan transaksi (Wait-Die dan Wound-Wait) untuk menghindari deadlock dalam skenario transaksi bersamaan. OOP dan abstraksi berhubungan erat dengan eksperimen ini karena:

1. **Pengelompokan Logika yang Mirip:**
   Kelas Transaction menyederhanakan operasi transaksi (pemesanan tiket dan pencarian tiket) dengan mengelompokkannya ke dalam satu entitas. Kita tidak perlu memikirkan detail implementasi setiap kali ingin melakukan transaksi; cukup memanggil metode booking atau search.

2. **Mengabstraksikan Resource:**
   Kelas Resource mengelola penguncian (locking) sumber daya seperti seat dan payment. Kita tidak perlu memikirkan detail bagaimana penguncian dilakukan, karena abstraksi ini sudah disediakan oleh kelas Resource.

3. **Penjadwalan dengan Abstraksi yang Jelas:**
   Kelas Scheduler menyediakan cara yang jelas dan modular untuk menjalankan berbagai skema penjadwalan seperti Wait-Die dan Wound-Wait. Dengan demikian, kita bisa dengan mudah mengganti strategi penjadwalan hanya dengan mengubah argumen ketika memanggil Scheduler.

## Mengapa Menggunakan Abstraksi dan OOP untuk Eksperimen Ini?

1. **Menyederhanakan Kode dan Meningkatkan Reusability:**
   Dengan menggunakan OOP, kita dapat mendefinisikan transaksi sebagai objek yang dapat dengan mudah digunakan kembali di bagian lain kode. Ini meningkatkan modularitas kode dan memungkinkan kita menghindari pengulangan logika. Misalnya, kita bisa menggunakan kembali metode booking dan search tanpa harus menulis ulang logika.

2. **Isolasi Tanggung Jawab:**
   OOP memungkinkan kita untuk mengisolasi tanggung jawab dalam kode. Kelas Transaction bertanggung jawab atas operasi transaksi, kelas Resource bertanggung jawab untuk mengelola sumber daya, dan kelas Scheduler bertanggung jawab untuk menangani penjadwalan transaksi. Ini memisahkan logika menjadi bagian-bagian yang mudah dikelola dan dipahami.

3. **Fleksibilitas:**
   Jika kita ingin menambahkan strategi penjadwalan baru atau tipe resource baru, kita dapat dengan mudah memperluas kode tanpa harus memodifikasi banyak bagian lain. Sebagai contoh, kita bisa menambahkan strategi penjadwalan lain ke dalam kelas Scheduler tanpa mengubah kode transaksi atau resource.

4. **Keterbacaan Kode:**
   Kode yang menggunakan abstraksi dan OOP lebih mudah dibaca dan dipahami, terutama dalam sistem yang kompleks. Kita dapat melihat hubungan antar-objek dan bagaimana operasi tertentu dilakukan tanpa harus terjebak dalam detail implementasi yang berulang.

5. **Mengatasi Kompleksitas:**
   Eksperimen ini melibatkan penjadwalan transaksi dan penguncian sumber daya, yang bisa sangat kompleks. Dengan abstraksi, kita dapat menangani kompleksitas ini dengan membuat kode yang lebih terstruktur dan terorganisir. Abstraksi membantu memecah masalah besar menjadi bagian-bagian kecil yang mudah dipahami.

## Kesimpulan

Dalam eksperimen ini, OOP dan abstraksi membantu menyederhanakan pengelolaan transaksi bersamaan yang melibatkan penguncian sumber daya (seperti seat dan payment) serta penjadwalan untuk mencegah deadlock. Abstraksi memungkinkan kita untuk fokus pada apa yang dilakukan transaksi dan penjadwalan tanpa perlu khawatir tentang bagaimana itu dilakukan. Dengan OOP, kita bisa mengisolasi logika, membuat kode lebih modular, meningkatkan keterbacaan, dan membuat sistem yang mudah diperluas.

- Mengurangi potensi deadlock
- Meningkatkan modularitas
- Memudahkan debugging
