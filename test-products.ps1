$Base = "http://localhost:8080/api/products"

# adauga un produs nou
$body = @{ name = "Laptop"; price = 2500.00; quantity = 3 }
$created = Invoke-RestMethod -Uri $Base -Method Post -ContentType "application/json" -Body ($body | ConvertTo-Json)
"Created: $($created | ConvertTo-Json -Compress)"

# obtine toate produsele
$allProducts = Invoke-RestMethod -Uri $Base -Method Get
"All Products: $($allProducts | ConvertTo-Json -Compress)"

# obtine produsul creat prin ID
$productById = Invoke-RestMethod -Uri "$Base/$($created.id)" -Method Get
"Product by ID: $($productById | ConvertTo-Json -Compress)"

# obtine produsul creat prin nume
$productByName = Invoke-RestMethod -Uri "$Base/name/$($created.name)" -Method Get
"Product by Name: $($productByName | ConvertTo-Json -Compress)"

$id = $created.id

# sterge produsul creat
Invoke-RestMethod -Uri "$Base/$id" -Method Delete
"Deleted product with ID: $id"

# incearca sa obtii produsul sters (ar trebui sa returneze o eroare)
try {
    $deletedProduct = Invoke-RestMethod -Uri "$Base/$id" -Method Get
    "Deleted Product: $($deletedProduct | ConvertTo-Json -Compress)"
} catch {
    "Error retrieving deleted product: $($_.Exception.Message)"
}