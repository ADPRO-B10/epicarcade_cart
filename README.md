 ## PROFILING FOR `list-reviews`

![alt text](image.png)

## PROFILING FOR `games`

![alt text](image-1.png)

## PROFILING FOR `delete-review`

![alt text](image-2.png)

## PROFILING FOR `add-review`

![alt text](image-3.png)

## HASIL ANALISIS

Setelah saya analisis dari pemanggilan 4 metode berbeda, ternyata seluruh method **GET** memiliki execution time terbanyak dari yang lain. Hal ini disebabkan karena method **GET** saya mengambil seluruh data yang dibutuhkan. Saya berasumsi bahwa semua pemrosesan seluruh data memerlukan lebih banyak waktu dibanding yang lain.