# Fungsionalitas Inti EpicArcade🎮
## Softaware Architecture - Microservices architecture

<p align="center" width="100%">
    <img width="60%" src="https://github.com/ADPRO-B10/epicarcade_functional/assets/112263712/75515bd8-4188-4925-b9ed-bbd5a7d0630d">
</p>

- Sistem terbagi menjadi layanan yang lebih kecil dan independen.
- Setiap layanan menangani fungsi tertentu, sehingga lebih mudah untuk diukur dan dipelihara
  
## 1. Pencarian dan Filtrasi Game (🙋, 🧑‍, 🤖)
## 2. Pembelian dan Penjualan Produk Game (🙋, 🧑)
## 3. Riwayat Transaksi (🙋, 🧑‍)
## 4. Pengelolaan Keranjang Belanja (🙋) - Soros Febriano (2206083445)
  ### Design Pattern - Data Access Object (DAO)
Data Access Object adalah design pattern yang memiliki tujuan utamanya adalah menyediakan antarmuka abstrak untuk mengakses database (supabase sql) atau layanan web (gcp), sambil menyembunyikan detail sumber data yang mendasarinya. Design pattern ini sangat cocok untuk aplikasi yang sering mengakses sebuah database dan mengakses data dari layer lain. Design pattern ini mirip seperti Repository tetapi DAO dikatakan lebih 'table-centric' dan dekat pada data itu sendiri daripada Repository yang lebih high level yang lebih dekat pada domain object. Saya memilih design pattern ini karena berencana berkomunikasi dengan microservices lain melalui database


![image](https://github.com/ADPRO-B10/epicarcade_functional/assets/112263712/a07964e9-6e24-42ff-8f06-93658e74f9db)
*source: https://www.youtube.com/channel/UChwKlOVR041tngjerWxVccw*

## 5. Rating dan Ulasan Produk (🙋, 🧑‍) 
 ### PROFILING FOR `list-reviews`

![alt text](image.png)

  ### PROFILING FOR `games`

![alt text](image-1.png)

  ### PROFILING FOR `delete-review`

![alt text](image-2.png)

  ### PROFILING FOR `add-review`

![alt text](image-3.png)

  ### HASIL ANALISIS

Setelah saya analisis dari pemanggilan 4 metode berbeda, ternyata seluruh method **GET** memiliki execution time terbanyak dari yang lain. Hal ini disebabkan karena method **GET** saya mengambil seluruh data yang dibutuhkan. Saya berasumsi bahwa semua pemrosesan seluruh data memerlukan lebih banyak waktu dibanding yang lain.

