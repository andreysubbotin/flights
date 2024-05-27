# Flight controller

```kotlin
val start = dateMin.atStartOfDay(ZoneId.systemDefault()).toOffsetDateTime()
val end = dateMax.atStartOfDay(ZoneId.systemDefault()).plusDays(1).toOffsetDateTime()
```

# Flight test by ID
id:
```kotlin
val id = "6" 
```
expects:
```kotlin
.andExpect(jsonPath("$.airlineCode").value("BA")) 
```

# Snippets for OpenAPI schema

```json
"security": [
{
"basic": []
}
],
```

```json
, 
"securitySchemes": { 
 "basic": { 
 "type": "http", 
 "scheme": "basic" 
 } 
} 
```
