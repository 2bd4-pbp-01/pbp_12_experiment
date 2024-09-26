# 2D Graphics with OpenGL and Haskell

## Pre-Requisite

Sebelum menjalankan program OpenGL dengan Haskell. Ada beberapa
prasayarat yang harus dilakukan agar program dapat berjalan.

1. Install OpenGL via [Mesa (2D & 3D graphics library)](https://www.mesa3d.org/)

   > [!NOTE]
   > Jika Mesa atau OpenGL tidak dapat di install, mungkin
   > penyebabnya karena library atau SDK untuk Window System
   > pada Sistem Operasi, Windows dan [Xorg](https://www.x.org/wiki/) atau
   > [Wayland](https://wayland.freedesktop.org/) pada Linux, yang
   > digunakan belum terinstall atau terkonfigurasi.

2. Install Haskell Toolchain

   Hal ini meliputi GHC Compiler, build tools dengan [Stack](https://docs.haskellstack.org/en/stable/),
   dan Language Server (opsional). Seluruh toolchain yang dibutuhkan dapat
   di-install melalui bantuan [GHCup](https://www.haskell.org/ghcup/#).

## Menjalankan Program

> [!IMPORTANT]
> Pastikan seluruh [pre-requisite](#pre-requisite) sudah terinstall
> dan terkonfigurasi dengan baik. Jika tidak, program
> tidak akan bisa dijalankan.

Semua tahap pada proses ini berbasis CLI (Command Line Interface)
sehingga, setiap prosesnya dijalankan di terminal.

1. Clone repository ini

   ```sh
   git clone https://github.com/2bd4-pbp-01/pbp_12_experiment.git
   ```

2. Masuk ke project `haskell-opengl`

   ```sh
   cd pbp_12_experiment/haskell-opengl

   ```

3. Install library / depdendencies eksternal yang diperlukan

   ```sh
   stack install
   ```

4. Pilih modul sesuai objek yang akan di render

   Buka file `app/Main.hs`. Terdapat potongan kode seperti dibawah ini:

   ```hs
   ...

   main = B.main
   -- main = DT.main
   -- main = P.main
   -- main = H.main
   ```

   Pilih salah satu library yang akan di jalankan dengan melakukan
   _un-comment_ pada library yang berkaitan dan memberikan _comment_
   pada library lainnya.

   Kondisi file diatas ketika dijalankan akan menampilkan program
   dari library yang block (ditandain dengan nama `B.main`). Misalnya
   ingin menjalankan library Portal (ditandai dengan nama `P.main`).
   Maka dapat mengubahnya menjadi seperti kode dibawah:

   ```hs
   ...

   -- main = B.main
   -- main = DT.main
   main = P.main
   -- main = H.main
   ```

5. Build / Compile Program

   Sebelum dapat dijalankan, program perlu di compile terlebih dahulu.
   Untuk melakukannya dapat menggunakan perintah dibawah:

   ```sh
   stack build
   ```

   > [!TIP]
   > Apabila terdapat warning berkaitan dengan file `Main.hs`,
   > hal ini karena terdapat library yang di-import tetapi tidak
   > digunakan. Warning tersebut **dapat diabaikan** atau jika merasa
   > mengganggu, dapat menghapus atau memberikan comment pada
   > library yang tidak digunakan.

6. Jalankan Program

   ```sh
   stack exec haskell-opengl-exe
   ```

   > [!NOTE]
   > Nama `haskell-opengl-exe` berasal dari file [`package.yaml`](./package.yaml)
   > pada bagian property bernama `executables`. File ini
   > dihasilkan secara otomatis oleh [stack](https://docs.haskellstack.org/en/stable/).

## Pengembangan Library

Library dapat didefinisikan pada direktori / folder [`src/`](./src/).
Sedangkan direktori / folder [`app/`](./app/) digunakan sebagai
entry point dari library untuk dijalankan. Pada direktori /
folder [`app/`](./app/) juga dapat didefinisikan sebagai package
executable dengan mendefinisikannya pada file [`package.yaml`](./package.yaml)
pada bagian property `executables`. Terdapat direktori / folder
[`test/`](./test/) yang berisi modul untuk membuat unit-test pada
setiap library yang telah dibuat.
