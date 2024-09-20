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

# Functional Programming dan Eksperimen Transaction Scheduling

## Pengertian Functional Programming (FP)

Functional Programming (FP) adalah paradigma pemrograman yang memperlakukan komputasi sebagai evaluasi fungsi matematika dan menghindari perubahan status serta data mutable (berubah). Beberapa fitur utama dari FP meliputi:

- First-class functions (fungsi yang dapat diperlakukan sebagai nilai)
- Higher-order functions (fungsi yang dapat menerima atau mengembalikan fungsi lain)
- Immutability (data yang tidak dapat diubah)
- Pure functions (fungsi yang selalu memberikan hasil yang sama untuk input yang sama tanpa efek samping)

## Hubungan Functional Programming dengan Eksperimen

Eksperimen ini melibatkan scheduling transaksi dan penanganan deadlock, yang membutuhkan penanganan status dari berbagai transaksi yang bersaing untuk mengakses sumber daya bersama (seperti lock pada seat dan payment). Functional programming berhubungan dengan eksperimen ini dalam beberapa cara:

### Immutability dan Side Effects

- Dalam eksperimen ini, state management (seperti lock pada sumber daya) adalah pusat dari potensi deadlock. FP menekankan immutability, yang berarti data tidak boleh diubah begitu ia diciptakan.
- Menggunakan FP dapat mengurangi kompleksitas side effects, yaitu perubahan state global, yang sering menjadi penyebab deadlock.

### Concurrency dan Parallelism

- Functional programming sangat cocok untuk sistem concurrent dan parallel karena immutability mencegah race conditions.
- Dalam eksperimen ini, kita menangani banyak transaksi yang berjalan secara bersamaan. Pendekatan FP bisa membantu membuat sistem lebih aman dari masalah deadlock.

### Higher-Order Functions dan Scheduling

- Eksperimen ini melibatkan penjadwalan transaksi. Higher-order functions memungkinkan pemisahan logika transaksi dan penjadwalan dengan lebih bersih.
- Dalam FP, Anda bisa membuat fungsi yang menangani transaksi secara murni berdasarkan input dan output tanpa mengubah state global.

## Mengapa Functional Programming Berhubungan dengan Eksperimen ini?

1. **Immutability Menghindari Deadlock**
   - Deadlock sering terjadi karena dua atau lebih transaksi mencoba mengunci sumber daya dalam urutan yang berbeda.
   - Dalam pendekatan FP, setiap transaksi dapat dianggap sebagai fungsi murni yang tidak mengubah state sumber daya bersama secara langsung.

2. **Concurrency yang Aman**
   - Data immutable memungkinkan pemrograman concurrent atau parallel berjalan tanpa risiko deadlock.
   - Semua transaksi bekerja dengan salinan dari data, yang dapat dievaluasi secara independen.

3. **Modular dan Declarative Code**
   - FP mendorong pendekatan yang lebih declarative, mendefinisikan "apa yang ingin dilakukan" tanpa terlalu peduli tentang "bagaimana" transaksi diimplementasikan.
   - Ini membuat kode lebih modular dan lebih mudah dimengerti.

## Mengapa Menggunakan Functional Programming untuk Eksperimen ini?

1. **Mengurangi Kesalahan akibat Side Effects**
   - Dengan FP, kita meminimalkan side effects dan hanya bekerja dengan data immutable.
   - Hal ini mengurangi potensi kesalahan ketika dua transaksi mencoba memodifikasi sumber daya yang sama.

2. **Concurrency yang Lebih Mudah**
   - FP menyediakan fitur bawaan seperti lazy evaluation, pure functions, dan higher-order functions yang memudahkan menangani program concurrent atau parallel.
   - FP memfasilitasi pengelolaan transaksi concurrent dengan lebih aman dan efisien.

3. **Debugging yang Lebih Mudah**
   - Transaksi dapat dipecah menjadi pure functions yang dapat diuji dan dievaluasi secara independen.
   - Debugging dan pengelolaan penjadwalan transaksi menjadi lebih mudah dibandingkan dengan pendekatan imperatif.

## Contoh Pendekatan Functional Programming untuk Eksperimen Transaction Scheduling

Dalam pendekatan FP, fokus akan lebih pada:

- Membangun transaksi sebagai pure functions yang tidak mengubah state global.
- Menggunakan fungsi yang menerima state sistem dan mengembalikan state baru tanpa mengubah sumber daya secara langsung.
- Menghindari mutable state yang berpotensi menyebabkan deadlock.

Alih-alih menggunakan lock langsung, pendekatan FP akan membuat transaksi hanya membaca data, memproses, dan mengembalikan hasil tanpa mengubah state global.

## Kesimpulan

Functional programming memiliki hubungan erat dengan eksperimen transaction scheduling karena fitur-fiturnya seperti immutability, pure functions, dan concurrency. FP menawarkan solusi yang lebih aman dan modular untuk menangani transaksi concurrent dan mencegah deadlock. Menggunakan pendekatan FP dalam eksperimen ini akan membantu:

- Mengurangi potensi deadlock
- Meningkatkan modularitas
- Memudahkan debugging
