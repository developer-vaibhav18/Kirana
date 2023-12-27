# Kirana
Steps to start application -> 
1. Start mongodb on its default port 27017.
2. Start spring boot project on intellij.
3. API to save transaction ->
```
curl --location --request POST 'http://localhost:8080/kirana/transactions/save' \
--header 'Content-Type: application/json' \
--data-raw '{
  "payer" : "Vaibhav", 
  "amount" : 5,
  "currency" : "INR",
  "reason" : "Vaibhav bought groceries",
  "transactionType" : "CREDIT"
}
'
```
4. API to fetch transactions based on start and end time ->
```
curl --location --request POST 'http://localhost:8080/kirana/transactions/fetch' \
--header 'Content-Type: application/json' \
--data-raw '{
    "startTime" : 3288,
    "endTime" : 3290,
    "currency" : "USD"
}
'
```
