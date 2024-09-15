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
